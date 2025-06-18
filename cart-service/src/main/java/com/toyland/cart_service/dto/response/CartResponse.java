package com.toyland.cart_service.dto.response;

import com.toyland.cart_service.entity.CartItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    String cartId;
    String userId;
    List<CartItemResponse> cartItems;
    int totalPrice = 0;
    int totalQuantity = 0;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
