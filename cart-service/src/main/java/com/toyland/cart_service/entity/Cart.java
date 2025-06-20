package com.toyland.cart_service.entity;

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
@Entity
@Table
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String cartId;

    @Column(unique = true)
    String userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CartItem> cartItems = new ArrayList<>();

    @Builder.Default
    int totalPrice = 0;

    @Builder.Default
    int totalQuantity = 0;

    @CreatedDate
    LocalDateTime createdAt;
    @LastModifiedDate
    LocalDateTime updatedAt;

    public void updateTotalQuantity() {
        int quantity = 0;
        for (CartItem item : this.cartItems) {
            quantity += item.getQuantity();
        }
        this.totalQuantity = quantity;
    }


    public void updateTotalPrice() {
        int price = 0;
        for (CartItem item : this.cartItems) {
            price += (item.getQuantity() * item.getProductPrice());
        }
        this.totalPrice = price;
    }
}
