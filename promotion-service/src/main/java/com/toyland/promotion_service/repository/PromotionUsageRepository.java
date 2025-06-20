package com.toyland.promotion_service.repository;

import com.toyland.promotion_service.entity.PromotionUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionUsageRepository extends JpaRepository<PromotionUsage, String> {
}
