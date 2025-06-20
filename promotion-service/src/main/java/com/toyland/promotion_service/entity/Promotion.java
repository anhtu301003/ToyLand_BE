package com.toyland.promotion_service.entity;

import com.toyland.promotion_service.Enum.DiscountTypeEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String promotionId;

    String promotionName;

    String promotionDescription;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    DiscountTypeEnum discountType;

    int discountValue;

    int minOrderAmount;

    @Column(nullable = false, unique = true)
    String giftCode;

    @Column(nullable = false)
    LocalDateTime startDate;

    @Column(nullable = false)
    LocalDateTime endDate;

    int usageLimit;

    @Builder.Default
    int usageCount = 0;

    @Builder.Default
    Boolean isActive = true;

    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<PromotionUsage> promotionUsages = new HashSet<>();

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    public void updateUsageCount() {
        this.usageCount = this.promotionUsages.size();
    }

    public void updateCheckActive() {
        if (this.usageCount == this.usageLimit) {
            this.isActive = false;
        }
    }
}
