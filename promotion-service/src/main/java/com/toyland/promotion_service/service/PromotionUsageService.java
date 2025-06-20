package com.toyland.promotion_service.service;

import com.toyland.promotion_service.dto.request.PromotionUsageRequest;
import com.toyland.promotion_service.dto.request.ValidatePromotionRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import com.toyland.promotion_service.entity.Promotion;
import com.toyland.promotion_service.entity.PromotionUsage;
import com.toyland.promotion_service.exception.AppException;
import com.toyland.promotion_service.exception.ErrorCode;
import com.toyland.promotion_service.mapper.PromotionMapper;
import com.toyland.promotion_service.mapper.PromotionUsageMapper;
import com.toyland.promotion_service.repository.PromotionRepository;
import com.toyland.promotion_service.repository.PromotionUsageRepository;
import com.toyland.promotion_service.service.IService.IPromotionUsageService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionUsageService implements IPromotionUsageService {
    PromotionUsageRepository promotionUsageRepository;
    PromotionUsageMapper promotionUsageMapper;
    PromotionRepository promotionRepository;
    private final PromotionMapper promotionMapper;

    @Override
    @Transactional
    public PromotionResponse userUsePromotion(String giftCode, PromotionUsageRequest promotionUsageRequest) {
        Optional<Promotion> promotionOptional = promotionRepository.findPromotionByGiftCode(giftCode);
        PromotionUsage promotionUsage = promotionUsageMapper.toPromotionUsage(promotionUsageRequest);


        if (promotionOptional.isPresent()) {
            Promotion promotion = promotionOptional.get();

            if (!promotion.getIsActive()) {
                throw new AppException(ErrorCode.PROMOTION_NOT_ACTIVE);
            }
            boolean check = promotion.getPromotionUsages().stream()
                    .anyMatch(usage -> usage.getUserId().equals(promotionUsage.getUserId()));

            if (check) {
                throw new AppException(ErrorCode.PROMOTION_USED);
            }

            promotionUsage.setPromotion(promotion);
            promotion.getPromotionUsages().add(promotionUsage);
            promotion.updateUsageCount();
            promotion.updateCheckActive();
            return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
        } else {
            throw new AppException(ErrorCode.PROMOTION_NOT_EXIST);
        }
    }

    @Override
    public PromotionResponse checkPromotion(String giftCode, ValidatePromotionRequest validatePromotionRequest) {
        Optional<Promotion> promotionOptional = promotionRepository.findPromotionByGiftCode(giftCode);
        if (promotionOptional.isPresent()) {
            Promotion promotion = promotionOptional.get();

            if (!promotion.getIsActive()) {
                throw new AppException(ErrorCode.PROMOTION_NOT_ACTIVE);
            }
            boolean check = promotion.getPromotionUsages().stream()
                    .anyMatch(usage -> usage.getUserId().equals(validatePromotionRequest.getUserId()));

            if (check) {
                throw new AppException(ErrorCode.PROMOTION_USED);
            }
            return promotionMapper.toPromotionResponse(promotion);
        } else {
            throw new AppException(ErrorCode.PROMOTION_NOT_EXIST);
        }
    }
}
