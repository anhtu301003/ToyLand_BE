package com.toyland.user_service.controller;

import com.toyland.user_service.dto.ApiResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserManagementController {

    UserProfileService userProfileService;

    @GetMapping
    public ApiResponse<Page<UserProfileResponse>> getAllProfiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ApiResponse.<Page<UserProfileResponse>>builder()
                .result(userProfileService.getAllProfiles(PageRequest.of(page,size)))
                .build();
    }
//
//    @PutMapping
//    public ApiResponse<UserProfileResponse> updateUserToAdmin(String userId){
//        return ApiResponse.<UserProfileResponse>builder()
//                .result(userProfileService.updateUserToAdmin())
//                .build();
//    }
}
