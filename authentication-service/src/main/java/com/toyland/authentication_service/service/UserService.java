package com.toyland.authentication_service.service;

import com.toyland.authentication_service.constant.PredefinedRole;
import com.toyland.authentication_service.dto.request.UserCreationRequest;
import com.toyland.authentication_service.dto.request.UserUpdateRequest;
import com.toyland.authentication_service.dto.response.UserResponse;
import com.toyland.authentication_service.entity.Role;
import com.toyland.authentication_service.entity.User;
import com.toyland.authentication_service.exception.AppException;
import com.toyland.authentication_service.exception.ErrorCode;
import com.toyland.authentication_service.mapper.UserMapper;
import com.toyland.authentication_service.mapper.UserProfileMapper;
import com.toyland.authentication_service.repository.RoleRepository;
import com.toyland.authentication_service.repository.UserRepository;
import com.toyland.authentication_service.repository.httpclient.UserClient;
import com.toyland.event.dto.CreateCartEvent;
import com.toyland.event.dto.NotificationEvent;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    UserClient userClient;
    UserProfileMapper userProfileMapper;
    KafkaTemplate<String, Object> kafkaTemplate;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        user = userRepository.save(user);

        var userProfileRequest = userProfileMapper.toProfileCreationRequest(request);
        userProfileRequest.setUserId(user.getId());
        userProfileRequest.setAdmin(false);

        try {
            userClient.createProfile(userProfileRequest);
        } catch (FeignException e) {
            System.out.print(e.getMessage());
        }

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .channel("EMAIL")
                .recipient(request.getEmail())
                .subject("Welcome to Toyland")
                .body("Hello, " + user.getUsername())
                .build();

        //Publish message to kafka
        kafkaTemplate.send("notification-delivery", notificationEvent);

        CreateCartEvent cartEvent = CreateCartEvent.builder()
                .userId(user.getId())
                .build();

        kafkaTemplate.send("cart-delivery", cartEvent);

        return userMapper.toUserResponse(user);
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public Boolean validateEmail(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setEmailVerified(true);
        return user.getEmailVerified();
    }
}
