package com.toyland.user_service.controller;

import com.toyland.event.dto.CreateUserEvent;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class KafkaUserListener {

    UserProfileService userProfileService;
    @KafkaListener(topics = "user-delivery")
    public void CreateUserDelivery(CreateUserEvent event) {
        log.info("Received CreateUserEvent: {}", event);
        userProfileService.createUserProfile(event);
    }
}
