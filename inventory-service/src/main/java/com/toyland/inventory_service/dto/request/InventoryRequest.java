package com.toyland.inventory_service.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRequest {
    String productId;

    String productName;

    int quantity;

    int lowStockThreshold;

    String warehouseId;
}
