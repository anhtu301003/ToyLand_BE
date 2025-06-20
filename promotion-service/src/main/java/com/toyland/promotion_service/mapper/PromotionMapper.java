package com.toyland.promotion_service.mapper;

import com.toyland.promotion_service.dto.request.PromotionRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import com.toyland.promotion_service.entity.Promotion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {PromotionUsageMapper.class})
public interface PromotionMapper {

    @Mapping(target = "promotionId", ignore = true)
    @Mapping(target = "promotionUsages", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Promotion toPromotion(PromotionRequest promotionRequest);

    PromotionResponse toPromotionResponse(Promotion promotion);

    void updatePromotionFromRequest(@MappingTarget Promotion promotion, PromotionRequest promotionRequest);
}
