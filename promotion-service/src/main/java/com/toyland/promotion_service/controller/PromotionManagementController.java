package com.toyland.promotion_service.controller;

import com.toyland.promotion_service.dto.ApiResponse;
import com.toyland.promotion_service.dto.request.PromotionRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import com.toyland.promotion_service.service.PromotionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PromotionManagementController {
    PromotionService promotionService;

    @GetMapping("")
    public ApiResponse<Page<PromotionResponse>> getPromotionBySearch(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String promotionName,
            @RequestParam(required = false) String promotionId,
            @RequestParam(required = false) String discountType,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String giftCode,
            @RequestParam(defaultValue = "createdAt,desc") String[] sort
    ) {
        Sort sortObj = Sort.by(Sort.Direction.fromString(sort[1]), sort[0]);
        Pageable pageable = PageRequest.of(page - 1, size, sortObj);

        return ApiResponse.<Page<PromotionResponse>>builder()
                .result(promotionService.getPromotionBySearch(pageable, promotionName, promotionId, discountType, isActive, giftCode))
                .build();
    }
}
