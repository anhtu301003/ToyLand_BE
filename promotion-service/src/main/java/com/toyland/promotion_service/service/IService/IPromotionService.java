package com.toyland.promotion_service.service.IService;

import com.toyland.promotion_service.dto.request.PromotionRequest;
import com.toyland.promotion_service.dto.request.UpdateStatusRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPromotionService {
    PromotionResponse createPromotion(PromotionRequest promotionRequest);

    PromotionResponse updatePromotion(String promotionId, PromotionRequest promotionRequest);

    PromotionResponse getPromotionByPromotionId(String promotionId);

    String deletePromotionByPromotionId(String promotionId);

    Page<PromotionResponse> getPromotionBySearch(Pageable pageable, String promotionName, String promotionId, String discountType, Boolean isActive, String giftCode);


    PromotionResponse updateActive(String promotionId, UpdateStatusRequest updateStatusRequest);
}
