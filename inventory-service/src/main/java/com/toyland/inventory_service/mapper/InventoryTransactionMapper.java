package com.toyland.inventory_service.mapper;

import com.toyland.inventory_service.dto.request.InventoryTransactionRequest;
import com.toyland.inventory_service.dto.response.InventoryTransactionResponse;
import com.toyland.inventory_service.entity.InventoryTransaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryTransactionMapper {
    InventoryTransaction toInventoryTransaction(InventoryTransactionRequest inventoryTransactionRequest);
    InventoryTransactionResponse toInventoryTransactionResponse(InventoryTransaction inventoryTransaction);
}
