package com.toyland.promotion_service.service;

import com.toyland.promotion_service.dto.request.PromotionRequest;
import com.toyland.promotion_service.dto.request.UpdateStatusRequest;
import com.toyland.promotion_service.dto.response.PromotionResponse;
import com.toyland.promotion_service.entity.Promotion;
import com.toyland.promotion_service.exception.AppException;
import com.toyland.promotion_service.exception.ErrorCode;
import com.toyland.promotion_service.mapper.PromotionMapper;
import com.toyland.promotion_service.repository.PromotionRepository;
import com.toyland.promotion_service.service.IService.IPromotionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PromotionService implements IPromotionService {
    PromotionRepository promotionRepository;
    PromotionMapper promotionMapper;

    @Override
    public PromotionResponse createPromotion(PromotionRequest promotionRequest) {
        Promotion promotion = promotionMapper.toPromotion(promotionRequest);
        return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
    }

    @Override
    public PromotionResponse updatePromotion(String promotionId, PromotionRequest promotionRequest) {
        Optional<Promotion> promotionOptional = promotionRepository.findById(promotionId);
        if (promotionOptional.isPresent()) {
            Promotion promotion = promotionOptional.get();
            promotionMapper.updatePromotionFromRequest(promotion, promotionRequest);
            return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
        } else {
            throw new AppException(ErrorCode.PROMOTION_NOT_EXIST);
        }
    }

    @Override
    public PromotionResponse getPromotionByPromotionId(String promotionId) {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);

        if (promotion.isPresent()) {
            return promotionMapper.toPromotionResponse(promotion.get());
        } else {
            throw new AppException(ErrorCode.PROMOTION_NOT_EXIST);
        }
    }

    @Override
    public String deletePromotionByPromotionId(String promotionId) {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);
        if (promotion.isPresent()) {
            promotionRepository.delete(promotion.get());
            return "Delete success promotion: " + promotion.get().getPromotionId();
        } else {
            throw new AppException(ErrorCode.PROMOTION_NOT_EXIST);
        }
    }

    @Override
    public Page<PromotionResponse> getPromotionBySearch(Pageable pageable, String promotionName, String promotionId, String discountType, Boolean isActive, String giftCode) {
        Specification<Promotion> spec = Specification.where(null);

        if (promotionName != null && !promotionName.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("promotionName")), "%" + promotionName.toLowerCase() + "%")
            );
        }

        if (promotionId != null && !promotionId.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("promotionId"), promotionId)
            );
        }

        if (discountType != null && !discountType.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("discountType"), discountType)
            );
        }

        if (isActive != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("isActive"), isActive)
            );
        }

        if (giftCode != null && !giftCode.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("giftCode")), "%" + giftCode.toLowerCase() + "%")
            );
        }

        Page<Promotion> resultPage = promotionRepository.findAll(spec, pageable);
        return resultPage.map(promotionMapper::toPromotionResponse);
    }

    @Override
    public PromotionResponse updateActive(String promotionId, UpdateStatusRequest updateStatusRequest) {
        Optional<Promotion> promotionOptional = promotionRepository.findById(promotionId);
        if (promotionOptional.isPresent()) {
            Promotion promotion = promotionOptional.get();
            promotion.setIsActive(updateStatusRequest.getIsActive());
            return promotionMapper.toPromotionResponse(promotionRepository.save(promotion));
        } else {
            throw new AppException(ErrorCode.PROMOTION_NOT_EXIST);
        }
    }


}
