package com.toyland.user_service.controller;

import com.toyland.user_service.dto.request.CreateUserRequest;
import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserEmailProfileResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUseProfileController {
    UserProfileService userProfileService;

    @PostMapping(value = "/internal", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody CreateUserRequest request) {
        return userProfileService.createUserProfile(request);
    }

    @GetMapping(value = "/internal/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserEmailProfileResponse getEmailProfile(@PathVariable String userId) {
        return userProfileService.getProfileEmail(userId);
    }
}
