package com.toyland.user_service.controller;

import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUseProfileController {
    UserProfileService userProfileService;

    @PostMapping(value = "/internal", produces = MediaType.APPLICATION_JSON_VALUE)
    UserProfileResponse createProfile(@RequestBody UserProfileRequest request) {
        System.out.print(request.toString());
        return userProfileService.createUserProfile(request);
    }
}
