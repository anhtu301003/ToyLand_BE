package com.toyland.order_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserOrderResponse {
    String userId;
    String fullName;
    String email;
    String phone;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
