package com.toyland.inventory_service.service.IService;

import com.toyland.event.dto.InventoryQuantityProductRequest;
import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IInventoryService {
    InventoryResponse createInventory(InventoryRequest request);

    List<InventoryResponse> getInventoryByProductId(String productId);


    String deleteInventory(String inventoryId);

    InventoryResponse updateInventory(String inventoryId, InventoryRequest request);

    Page<InventoryResponse> getAllInventories(String productName, PageRequest pageable);

    void updateInventory(InventoryQuantityProductRequest inventoryQuantityProductRequest);
}
