package com.toyland.cart_service.service;

import com.toyland.cart_service.dto.request.CartItemRequest;
import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.cart_service.entity.Cart;
import com.toyland.cart_service.entity.CartItem;
import com.toyland.cart_service.exception.AppException;
import com.toyland.cart_service.exception.ErrorCode;
import com.toyland.cart_service.mapper.CartItemMapper;
import com.toyland.cart_service.mapper.CartMapper;
import com.toyland.cart_service.repository.CartItemRepository;
import com.toyland.cart_service.repository.CartRepository;
import com.toyland.cart_service.service.IService.ICartService;
import com.toyland.event.dto.CreateCartEvent;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService implements ICartService {
    CartRepository cartRepository;
    CartItemRepository cartItemRepository;
    CartItemMapper cartItemMapper;
    CartMapper cartMapper;

    @Override
    public void createCart(CreateCartEvent createCartEvent) {
        Cart cart = cartMapper.toCart(createCartEvent);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public CartResponse updateCart(String userId, CartItemRequest cartItemRequest) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isEmpty()) {
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }

        Cart cart = cartOptional.get();

        List<CartItem> items = cart.getCartItems();

        for (CartItem cartItem : items) {
            if (cartItem.getProductId().equals(cartItemRequest.getProductId())) {
                cartItemMapper.updateCartItem(cartItemRequest, cartItem);
            }
        }

        cart.updateTotalQuantity();

        cart.updateTotalPrice();

        cartRepository.save(cart);

        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse getCart(String userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        if (cartOptional.isEmpty()) {
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }
        return cartMapper.toCartResponse(cartOptional.get());
    }

    @Override
    public CartResponse deleteAllItemFromCart(String cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isEmpty()) {
            throw new AppException(ErrorCode.CART_NOT_EXIST);
        }
        Cart cart = cartOptional.get();
        cart.getCartItems().clear();
        cart.setTotalQuantity(0);
        cart.setTotalPrice(0);
        return cartMapper.toCartResponse(cartRepository.save(cart));
    }


}
