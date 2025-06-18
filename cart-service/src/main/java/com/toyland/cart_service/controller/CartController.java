package com.toyland.cart_service.controller;

import com.toyland.cart_service.dto.ApiResponse;
import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.cart_service.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {
    CartService cartService;

    @PutMapping("/items")
    public ApiResponse<CartResponse> updateCart(@RequestBody CartItemRequest cartItemRequest) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaims().get("userId").toString();

        return ApiResponse.<CartResponse>builder()
                .result(cartService.updateCart(userId, cartItemRequest))
                .build();
    }

    @GetMapping("")
    public ApiResponse<CartResponse> getCart() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaims().get("userId").toString();

        return ApiResponse.<CartResponse>builder()
                .result(cartService.getCart(userId))
                .build();
    }

    @DeleteMapping("/{cartId}/items")
    public ApiResponse<CartResponse> deleteAllItemFromCart(@PathVariable String cartId) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaims().get("userId").toString();

        return ApiResponse.<CartResponse>builder()
                .result(cartService.deleteAllItemFromCart(cartId))
                .build();
    }
}
