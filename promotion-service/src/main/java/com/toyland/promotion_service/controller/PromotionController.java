package com.toyland.promotion_service.controller;

import com.toyland.promotion_service.dto.ApiResponse;
import com.toyland.promotion_service.dto.request.PromotionRequest;
import com.toyland.promotion_service.dto.request.UpdateStatusRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import com.toyland.promotion_service.service.PromotionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PromotionController {
    PromotionService promotionService;

    @PostMapping
    public ApiResponse<PromotionResponse> createPromotion(@RequestBody PromotionRequest promotionRequest) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.createPromotion(promotionRequest))
                .build();
    }

    @PutMapping("/{promotionId}")
    public ApiResponse<PromotionResponse> updatePromotion(@PathVariable String promotionId, @RequestBody PromotionRequest promotionRequest) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.updatePromotion(promotionId, promotionRequest))
                .build();
    }

    @GetMapping("/{promotionId}")
    public ApiResponse<PromotionResponse> getPromotionByPromotionId(@PathVariable String promotionId) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.getPromotionByPromotionId(promotionId))
                .build();
    }

    @DeleteMapping("/{promotionId}")
    public ApiResponse<String> deletePromotionByPromotionId(@PathVariable String promotionId) {
        return ApiResponse.<String>builder()
                .result(promotionService.deletePromotionByPromotionId(promotionId))
                .build();
    }

    @PatchMapping("/{promotionId}/toggle-active")
    public ApiResponse<PromotionResponse> updateActive(@PathVariable String promotionId, @RequestBody UpdateStatusRequest updateStatusRequest) {
        return ApiResponse.<PromotionResponse>builder()
                .result(promotionService.updateActive(promotionId, updateStatusRequest))
                .build();
    }
}
