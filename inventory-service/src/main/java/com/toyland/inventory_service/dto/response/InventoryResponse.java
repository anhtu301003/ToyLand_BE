package com.toyland.inventory_service.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    String transactionId;

    String inventoryId;

    String productId;

    String productName;

    int quantity;

    int lowStockThreshold;

    LocalDateTime lastUpdate;

    String warehouseId;
}
