package com.toyland.authentication_service.configuration;

import com.toyland.authentication_service.constant.PredefinedRole;
import com.toyland.authentication_service.dto.request.ProfileCreationRequest;
import com.toyland.authentication_service.dto.request.UserCreationRequest;
import com.toyland.authentication_service.entity.Role;
import com.toyland.authentication_service.entity.User;
import com.toyland.authentication_service.repository.RoleRepository;
import com.toyland.authentication_service.repository.UserRepository;
import com.toyland.authentication_service.repository.httpclient.UserClient;
import com.toyland.event.dto.CreateUserEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    KafkaTemplate<String,Object> kafkaTemplate;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository, RoleRepository roleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByUsername(ADMIN_USER_NAME).isEmpty()) {
                roleRepository.save(Role.builder()
                        .name(PredefinedRole.USER_ROLE)
                        .description("User role")
                        .build());

                Role adminRole = roleRepository.save(Role.builder()
                        .name(PredefinedRole.ADMIN_ROLE)
                        .description("Admin role")
                        .build());

                var roles = new HashSet<Role>();
                roles.add(adminRole);

                User user = User.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build();

                User saveUser = userRepository.save(user);

                CreateUserEvent userCreationEvent = CreateUserEvent.builder()
                        .userId(saveUser.getId())
                        .email("admin@yopmail.com")
                        .fullName("Admin")
                        .admin(true)
                        .build();

                kafkaTemplate.send("user-delivery", userCreationEvent);

                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}