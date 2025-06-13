package com.toyland.inventory_service.dto.request;

import com.toyland.inventory_service.Enum.TransactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class InventoryTransactionRequest {
    String inventoryId;

    int quantity;

    TransactionType transactionType;

    LocalDateTime createdAt;

    String orderId;

    String description;
}
