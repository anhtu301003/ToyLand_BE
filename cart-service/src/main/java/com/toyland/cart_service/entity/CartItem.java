package com.toyland.cart_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String cartItemId;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    String productId;

    String productName;

    String productImage;

    int productPrice;

    int quantity;
    @CreatedDate
    LocalDateTime createdAt;
}
