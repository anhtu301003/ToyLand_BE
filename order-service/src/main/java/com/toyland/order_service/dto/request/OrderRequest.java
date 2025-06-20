package com.toyland.order_service.dto.request;

import com.toyland.order_service.Enum.OrderStatusEnum;
import com.toyland.order_service.dto.response.AddressOrderResponse;
import com.toyland.order_service.dto.response.OrderItemResponse;
import com.toyland.order_service.dto.response.UserOrderResponse;
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
    UserOrderRequest userOrder;
    AddressOrderRequest addressOrder;
    String orderDescription;
    int totalPrice;
    int totalQuantity;
    String paymentId;
    String paymentType;
    List<OrderItemRequest> orderItems;
}
