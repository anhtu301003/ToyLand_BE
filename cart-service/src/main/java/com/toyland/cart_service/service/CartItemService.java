package com.toyland.cart_service.service;

import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.cart_service.entity.Cart;
import com.toyland.cart_service.entity.CartItem;
import com.toyland.cart_service.exception.AppException;
import com.toyland.cart_service.exception.ErrorCode;
import com.toyland.cart_service.mapper.CartItemMapper;
import com.toyland.cart_service.mapper.CartMapper;
import com.toyland.cart_service.repository.CartItemRepository;
import com.toyland.cart_service.repository.CartRepository;
import com.toyland.cart_service.service.IService.ICartItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartItemService implements ICartItemService {
    CartItemRepository cartItemRepository;
    CartItemMapper cartItemMapper;
    CartMapper cartMapper;
    CartRepository cartRepository;

    @Override
    public CartResponse addToCart(String userId, CartItemRequest cartItemRequest) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        if (optCart.isEmpty()) {
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }

        Cart cart = optCart.get();
        List<CartItem> cartItems = cart.getCartItems();

        CartItem cartItemFromRequest = cartItemMapper.toCartItem(cartItemRequest);
        cartItemFromRequest.setCart(cart); // rất quan trọng

        boolean isUpdated = false;

        for (CartItem item : cartItems) {
            if (item.getProductId().equals(cartItemRequest.getProductId())) {
                item.setQuantity(item.getQuantity() + cartItemRequest.getQuantity());
                isUpdated = true;
                break;
            }
        }

        if (!isUpdated) {
            cartItems.add(cartItemFromRequest);
        }

        cart.updateTotalQuantity();

        cart.updateTotalPrice();
        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse deleteCartItem(String userId, String productId) {
        Optional<Cart> optCart = cartRepository.findByUserId(userId);
        if (optCart.isEmpty()) {
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }

        Cart cart = optCart.get();

        List<CartItem> cartItems = cart.getCartItems();

        cartItems.removeIf(cartItem -> cartItem.getProductId().equals(productId));

        cart.updateTotalQuantity();

        cart.updateTotalPrice();

        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

//    @Override
//    public CartResponse deleteCartItem(String ) {
//        Optional<Cart> optCart = cartRepository.findById(cartId);
//        if (optCart.isEmpty()) {
//            throw new AppException(ErrorCode.CART_NOT_EXIST);
//        }
//
//        Cart cart = optCart.get();
//
//        List<CartItem> cartItems = cart.getCartItems();
//
//        cartItems.removeIf(cartItem -> cartItem.getCartItemId().equals(cartItemId));
//
//        cart.updateTotalQuantity();
//
//        cart.updateTotalPrice();
//
//        cartRepository.save(cart); // Do cascade ALL nên tự cập nhật cartItems
//
//        return cartMapper.toCartResponse(cart);
//    }
}
