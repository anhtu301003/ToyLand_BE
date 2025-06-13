package com.toyland.user_service.controller;

import com.toyland.user_service.dto.ApiResponse;
import com.toyland.user_service.dto.request.UserAddressRequest;
import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserAddressResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserAddress;
import com.toyland.user_service.service.UserAddressService;
import com.toyland.user_service.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserAddressController {

    UserAddressService userAddressService;

    @GetMapping("/{userId}/addresses")
    public ApiResponse<List<UserAddressResponse>> getAddressByUserId(@PathVariable String userId) {
        return ApiResponse.<List<UserAddressResponse>>builder()
                .result(userAddressService.getAddressByUserId(userId))
                .build();
    }

    @PostMapping("/{userId}/addresses")
    public ApiResponse<UserProfileResponse> addAddressByUserId(@PathVariable String userId,@RequestBody UserAddressRequest userAddressRequest) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userAddressService.addAddressByUserId(userId,userAddressRequest))
                .build();
    }


    @PutMapping("/addresses/{addressId}")
    public ApiResponse<UserAddressResponse> updateAddressByAddressId(@PathVariable String addressId,@RequestBody UserAddressRequest userAddressRequest) {
        return ApiResponse.<UserAddressResponse>builder()
                .result(userAddressService.updateAddressByAddressId(addressId,userAddressRequest))
                .build();
    }

    @DeleteMapping("/addresses/{addressId}")
    public ApiResponse<String> deleteAddressByAddressId(@PathVariable String addressId) {
        return ApiResponse.<String>builder()
                .result(userAddressService.deleteAddressByAddressId(addressId))
                .build();
    }

    @PutMapping("/addresses/{addressId}/default")
    public ApiResponse<UserProfileResponse> updateAddressByDefault(@PathVariable String addressId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userAddressService.updateAddressByDefault(addressId))
                .build();
    }
}
