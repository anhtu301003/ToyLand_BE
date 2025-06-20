package com.toyland.cart_service.mapper;

import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.response.CartItemResponse;
import com.toyland.cart_service.entity.CartItem;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CartItemMapper {
    @Mapping(target = "cartItemId", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    CartItem toCartItem(CartItemRequest cartItemRequest);

    @Mapping(target = "cartId", source = "cart.cartId")
    CartItemResponse toCartResponse(CartItem cartItem);


    @Mapping(target = "cartItemId", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateCartItem(CartItemRequest cartItemRequest, @MappingTarget CartItem cartItem);
}
