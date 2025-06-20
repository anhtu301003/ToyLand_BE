package com.toyland.cart_service.service.IService;

import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.event.dto.CreateCartEvent;

public interface ICartService {

    void createCart(CreateCartEvent createCartEvent);

    CartResponse updateCart(String userId, CartItemRequest cartItemRequest);

    CartResponse getCart(String userId);

    CartResponse deleteAllItemFromCart(String cartId);

}
