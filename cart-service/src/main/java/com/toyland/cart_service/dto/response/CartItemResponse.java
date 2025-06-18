package com.toyland.cart_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {
    String cartItemId;
    String cartId;
    String productId;
    String productName;
    String productImage;
    int productPrice;
    int quantity;
    LocalDateTime createdAt;
}
