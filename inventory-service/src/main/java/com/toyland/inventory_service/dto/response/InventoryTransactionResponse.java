package com.toyland.inventory_service.dto.response;

import com.toyland.inventory_service.Enum.TransactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransactionResponse {

    String inventoryId;

    int quantity;

    TransactionType transactionType;

    LocalDateTime createdAt;

    String orderId;

    String description;
}
