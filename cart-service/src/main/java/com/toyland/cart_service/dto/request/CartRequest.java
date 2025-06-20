package com.toyland.cart_service.dto.request;

import com.toyland.cart_service.entity.CartItem;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartRequest {
    String userId;
    List<CartItemRequest> cartItems;
    int totalPrice = 0;
    int totalQuantity = 0;
}
