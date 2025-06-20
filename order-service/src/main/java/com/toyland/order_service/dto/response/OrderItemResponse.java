package com.toyland.order_service.dto.response;

import com.toyland.order_service.entity.Order;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
    List<String> productImage;
    int quantity;
    int price;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
