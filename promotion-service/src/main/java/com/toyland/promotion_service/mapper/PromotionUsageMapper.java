package com.toyland.promotion_service.mapper;

import com.toyland.promotion_service.dto.request.PromotionUsageRequest;
import com.toyland.promotion_service.dto.response.PromotionUsageResponse;
import com.toyland.promotion_service.entity.PromotionUsage;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PromotionUsageMapper {
    @Mapping(target = "promotionUsageId", ignore = true)
    @Mapping(target = "promotion", ignore = true)
    @Mapping(target = "usedAt", ignore = true)
    PromotionUsage toPromotionUsage(PromotionUsageRequest promotionUsageRequest);

    @Mapping(target = "promotionId", source = "promotion.promotionId")
    PromotionUsageResponse toPromotionUsageResponse(PromotionUsage promotionUsage);

    void updatePromotionUsageFromRequest(@MappingTarget PromotionUsage promotionUsage, PromotionUsageRequest promotionUsageRequest);
}
