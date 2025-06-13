package com.toyland.order_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
@EntityListeners(AuditingEntityListener.class)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String orderItemId;


    String orderId;

    String productId;

    String productName;

    String productImage;

    int quantity;

    BigDecimal unitPrice;

    BigDecimal subTotalPrice;

    @CreatedDate
    LocalDateTime createTime;
}
