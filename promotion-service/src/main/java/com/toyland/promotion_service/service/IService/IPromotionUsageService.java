package com.toyland.promotion_service.service.IService;

import com.toyland.promotion_service.dto.request.PromotionUsageRequest;
import com.toyland.promotion_service.dto.request.ValidatePromotionRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;

public interface IPromotionUsageService {
    PromotionResponse userUsePromotion(String giftCode, PromotionUsageRequest promotionUsageRequest);

    PromotionResponse checkPromotion(String giftCode, ValidatePromotionRequest validatePromotionRequest);
}
