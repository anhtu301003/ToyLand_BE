package com.toyland.cart_service.controller;

import com.toyland.cart_service.dto.ApiResponse;
import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.cart_service.service.CartItemService;
import com.toyland.cart_service.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@RequiredArgsConstructor
public class CartItemController {
    CartItemService cartItemService;
    CartService cartService;


    @PostMapping("/items")
    public ApiResponse<CartResponse> addToCart(@RequestBody CartItemRequest cartItemRequest) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = jwt.getClaims().get("userId").toString();

        return ApiResponse.<CartResponse>builder()
                .result(cartItemService.addToCart(userId, cartItemRequest))
                .build();
    }

    @DeleteMapping("/items/{productId}")
    public ApiResponse<CartResponse> deleteCartItem(@PathVariable String productId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String userId = jwt.getClaims().get("userId").toString();

        return ApiResponse.<CartResponse>builder()
                .result(cartItemService.deleteCartItem(userId, productId))
                .build();
    }
}
