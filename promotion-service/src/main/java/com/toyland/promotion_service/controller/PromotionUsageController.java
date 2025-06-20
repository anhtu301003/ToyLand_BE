package com.toyland.promotion_service.controller;

import com.toyland.promotion_service.dto.ApiResponse;
import com.toyland.promotion_service.dto.request.PromotionRequest;
import com.toyland.promotion_service.dto.request.PromotionUsageRequest;
import com.toyland.promotion_service.dto.request.ValidatePromotionRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import com.toyland.promotion_service.service.PromotionUsageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PromotionUsageController {
    PromotionUsageService promotionUsageService;

    @PostMapping("/apply/{giftCode}")
    public ApiResponse<PromotionResponse> userUsePromotion(@PathVariable String giftCode, @RequestBody PromotionUsageRequest promotionUsageRequest) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionUsageService.userUsePromotion(giftCode, promotionUsageRequest))
                .build();
    }

    @PostMapping("/validate/{giftCode}")
    public ApiResponse<PromotionResponse> userUsePromotion(@PathVariable String giftCode, @RequestBody ValidatePromotionRequest validatePromotionRequest) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionUsageService.checkPromotion(giftCode, validatePromotionRequest))
                .build();
    }
}
