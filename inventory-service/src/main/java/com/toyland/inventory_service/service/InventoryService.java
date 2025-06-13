package com.toyland.inventory_service.service;

import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import com.toyland.inventory_service.entity.Inventory;
import com.toyland.inventory_service.mapper.InventoryMapper;
import com.toyland.inventory_service.repository.InventoryRepository;
import com.toyland.inventory_service.service.IService.IInventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryService implements IInventoryService {
    InventoryRepository inventoryRepository;
    InventoryMapper inventoryMapper;
    LowStockNotificationService lowStockNotificationService;
    @Override
    public InventoryResponse createInventory(InventoryRequest request) {
        Inventory inventory = inventoryMapper.toInventory(request);
        lowStockNotificationService.checkAndNotifyLowStock(inventory);
        return inventoryMapper.toInventoryResponse(inventoryRepository.save(inventory));
    }

    @Override
    public List<InventoryResponse> getInventoryByProductId(String productId) {
        return inventoryRepository.findAllByProductId(productId).stream().map(inventoryMapper::toInventoryResponse).toList();
    }

    @Override
    public List<InventoryResponse> getAllInventories(Long warehouseId, Pageable pageable) {
        return inventoryRepository.findAll().stream().map(inventoryMapper::toInventoryResponse).toList();
    }

    @Override
    public String deleteInventory(String inventoryId) {
        if (!inventoryRepository.existsById(inventoryId)) {
            return "Inventory with id " + inventoryId + " does not exist";
        }
        try{
            inventoryRepository.deleteById(inventoryId);
        }catch (Exception e) {
            return e.getMessage();
        }
        return "Inventory deleted successfully";
    }

    @Override
    public InventoryResponse updateInventory(String inventoryId, InventoryRequest request) {
//        if(!inventoryRepository.existsById(inventoryId)) {
//            return ;
//        }
        Inventory inventory = inventoryRepository.findById(inventoryId).orElse(null);
        inventoryMapper.updateInventoryFromRequest(request, inventory);
        return inventoryMapper.toInventoryResponse( inventoryRepository.save(inventory));
    }

}
