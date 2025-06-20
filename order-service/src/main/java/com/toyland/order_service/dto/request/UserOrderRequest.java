package com.toyland.order_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderRequest {
    String userId;
    String fullName;
    String email;
    String phone;
}
