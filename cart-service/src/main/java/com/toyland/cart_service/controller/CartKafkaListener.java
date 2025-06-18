package com.toyland.cart_service.controller;

import com.toyland.cart_service.dto.ApiResponse;
import com.toyland.cart_service.dto.request.CartRequest;
import com.toyland.cart_service.dto.response.CartResponse;
import com.toyland.cart_service.service.CartService;
import com.toyland.event.dto.CreateCartEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartKafkaListener {
    CartService cartService;

    @KafkaListener(topics = "cart-delivery")
    public void createCart(CreateCartEvent createCartEvent) {
        cartService.createCart(createCartEvent);
    }
}
