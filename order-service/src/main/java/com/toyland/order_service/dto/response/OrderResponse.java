package com.toyland.order_service.dto.response;

import com.toyland.order_service.Enum.OrderStatusEnum;
import com.toyland.order_service.entity.OrderItem;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {
    String orderId;
    String userId;
    OrderStatusEnum orderStatus;
    int totalPrice;
    int totalQuantity;
    String shippingAddress;
    String paymentId;
    List<OrderItemResponse> orderItems;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
