package com.toyland.inventory_service.service.IService;

import com.toyland.inventory_service.dto.request.InventoryTransactionRequest;
import com.toyland.inventory_service.dto.response.InventoryTransactionResponse;

public interface IInventoryTransactionService {
    InventoryTransactionResponse createInventoryTransaction(InventoryTransactionRequest request);

    String deleteInventoryTransaction(String inventoryTransactionId);
}
