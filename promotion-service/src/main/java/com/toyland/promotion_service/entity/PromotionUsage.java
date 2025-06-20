package com.toyland.promotion_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class PromotionUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String promotionUsageId;

    @ManyToOne
    @JoinColumn(name = "promotion_id", nullable = false)
    Promotion promotion;

    String userId;

    String userName;

    String orderId;

    @Column(name = "used_at")
    @Builder.Default
    LocalDateTime usedAt = LocalDateTime.now();
}
