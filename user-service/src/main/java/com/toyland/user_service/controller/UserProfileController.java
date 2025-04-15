package com.toyland.user_service.controller;

import com.toyland.user_service.dto.ApiResponse;
import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-profile")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ApiResponse<UserProfileResponse> getProfile(@PathVariable String id){
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getProfile(id))
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserProfileResponse>> getAllProfiles(){
        return ApiResponse.<List<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfiles())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<UserProfileResponse> updateUserProfile(@PathVariable String id,@RequestBody UserProfileRequest request){
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.updateUserProfile(id,request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUserProfile(@PathVariable String id){
        userProfileService.deleteUserProfile(id);
        return ApiResponse.<String>builder()
                .result("User profile deleted successfully")
                .build();
    }
}
