package com.toyland.promotion_service.repository;

import com.toyland.promotion_service.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String>, JpaSpecificationExecutor<Promotion> {
    Page<Promotion> findAll(Specification<Promotion> spec, Pageable pageable);

    Optional<Promotion> findPromotionByGiftCode(String giftCode);
}
