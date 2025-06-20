package com.toyland.promotion_service.dto.request;

import com.toyland.promotion_service.entity.Promotion;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionUsageRequest {
    String userId;
    String userName;
    String orderId;
}
