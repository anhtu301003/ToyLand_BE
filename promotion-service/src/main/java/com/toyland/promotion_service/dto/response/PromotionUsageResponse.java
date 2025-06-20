package com.toyland.promotion_service.dto.response;

import com.toyland.promotion_service.entity.Promotion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionUsageResponse {
    String promotionUsageId;
    String promotionId;
    String userId;
    String userName;
    String orderId;
    LocalDateTime usedAt;
}
