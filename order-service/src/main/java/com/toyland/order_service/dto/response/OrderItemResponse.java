package com.toyland.order_service.dto.response;

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
@Builder
public class OrderItemResponse {
    String orderItemId;

    String orderId;

    String productId;

    String productName;

    String productImage;

    int quantity;

    BigDecimal unitPrice;

    BigDecimal subTotalPrice;

    LocalDateTime createTime;
}
