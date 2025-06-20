package com.toyland.promotion_service.dto.response;

import com.toyland.promotion_service.Enum.DiscountTypeEnum;
import com.toyland.promotion_service.entity.PromotionUsage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromotionResponse {
    String promotionId;
    String promotionName;
    String promotionDescription;
    DiscountTypeEnum discountType;
    int discountValue;
    int minOrderAmount;
    String giftCode;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int usageLimit;
    int usageCount;
    Boolean isActive;
    Set<PromotionUsageResponse> promotionUsages;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
