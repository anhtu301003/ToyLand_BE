package com.toyland.promotion_service.dto.request;

import com.toyland.promotion_service.Enum.DiscountTypeEnum;
import com.toyland.promotion_service.entity.PromotionUsage;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionRequest {
    String promotionName;
    String promotionDescription;
    DiscountTypeEnum discountType;
    int discountValue;
    int minOrderAmount;
    String giftCode;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int usageLimit;
    Boolean isActive;
    Set<PromotionUsageRequest> promotionUsages;
}
