package com.toyland.inventory_service.service.IService;

import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInventoryService {
    InventoryResponse createInventory(InventoryRequest request);

    List<InventoryResponse> getInventoryByProductId(String productId);

    List<InventoryResponse> getAllInventories(Long warehouseId, Pageable pageable);

    String deleteInventory(String inventoryId);

    InventoryResponse updateInventory(String inventoryId, InventoryRequest request);
}
