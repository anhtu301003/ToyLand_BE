package com.toyland.cart_service.mapper;

import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.cart_service.entity.Cart;
import com.toyland.event.dto.CreateCartEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {CartItemMapper.class})
public interface CartMapper {
    Cart toCart(CartRequest cartRequest);

    CartResponse toCartResponse(Cart cart);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "cartItems", ignore = true)
    void updateCart(CartRequest cartRequest, @MappingTarget Cart cart);

    Cart toCart(CreateCartEvent createCartEvent);
}
