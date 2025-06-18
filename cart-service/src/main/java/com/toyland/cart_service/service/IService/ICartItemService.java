package com.toyland.cart_service.service.IService;

import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;

public interface ICartItemService {

    CartResponse addToCart(String userId, CartItemRequest cartitemRequest);

    CartResponse deleteCartItem(String userId, String productId);
}
