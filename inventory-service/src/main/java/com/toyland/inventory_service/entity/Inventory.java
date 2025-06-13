package com.toyland.inventory_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String inventoryId;

    String productId;

    String productName;

    int quantity;

    String warehouseId;

    @Builder.Default
    int lowStockThreshold = 10;

    @LastModifiedDate
    LocalDateTime lastUpdate;
}

