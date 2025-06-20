package com.toyland.order_service.dto.request;

import com.toyland.order_service.Enum.OrderStatusEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStatusOrder {
    String orderStatus;
}
