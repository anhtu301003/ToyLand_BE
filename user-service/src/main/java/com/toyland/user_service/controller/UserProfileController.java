package com.toyland.user_service.controller;

import com.toyland.user_service.dto.ApiResponse;
import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import com.toyland.user_service.exception.ErrorCode;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileController {
    UserProfileService userProfileService;


    @GetMapping("/profile")
    public ApiResponse<UserProfileResponse> getProfile(){
            Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            System.out.println(jwt.getClaims());
            String userId = jwt.getClaims().get("userId").toString();

            return ApiResponse.<UserProfileResponse>builder()
                    .result(userProfileService.getProfile(userId))
                    .build();
    }

    @PutMapping("/profile")
    public ApiResponse<UserProfileResponse> updateUserProfile(@RequestBody UserProfileRequest userProfileRequest){
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaims().get("userId").toString();
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.updateUserProfile(userId,userProfileRequest))
                .build();
    }

    @GetMapping("/{userid}")
    public ApiResponse<UserProfileResponse> getProfile(@PathVariable String userid){
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getProfile(userid))
                .build();
    }

    @PutMapping("/{userid}")
    public ApiResponse<UserProfileResponse> updateUserProfileByUserId(@PathVariable String userid,@RequestBody UserProfileRequest request){
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.updateUserProfile(userid,request))
                .build();
    }

    @DeleteMapping("/{userid}")
    public ApiResponse<String> deleteUserProfile(@PathVariable String userid){
        return ApiResponse.<String>builder()
                .result(userProfileService.deleteUserProfile(userid))
                .build();
    }
}
