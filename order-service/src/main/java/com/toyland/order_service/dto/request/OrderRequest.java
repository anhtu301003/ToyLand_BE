package com.toyland.order_service.dto.request;

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
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
    String userId;
    OrderStatusEnum orderStatus;
    int totalPrice;
    int totalQuantity;
    String paymentName;
    String paymentId;
    List<OrderItemRequest> orderItems;
}
