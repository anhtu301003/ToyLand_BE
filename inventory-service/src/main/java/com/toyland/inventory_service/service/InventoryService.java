package com.toyland.inventory_service.service;

import com.toyland.event.dto.ChangeProductRequest;
import com.toyland.event.dto.InventoryQuantityProductRequest;
import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import com.toyland.inventory_service.entity.Inventory;
import com.toyland.inventory_service.mapper.InventoryMapper;
import com.toyland.inventory_service.repository.InventoryRepository;
import com.toyland.inventory_service.service.IService.IInventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

//    @Override
//    public Page<InventoryResponse> getAllInventories(Long warehouseId, Pageable pageable) {
//        return inventoryRepository.findAll(pageable).map(inventoryMapper::toInventoryResponse);
//    }

    @Override
    public String deleteInventory(String inventoryId) {
        if (!inventoryRepository.existsById(inventoryId)) {
            return "Inventory with id " + inventoryId + " does not exist";
        }
        try {
            inventoryRepository.deleteById(inventoryId);
        } catch (Exception e) {
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
        return inventoryMapper.toInventoryResponse(inventoryRepository.save(inventory));
    }

    @Override
    public Page<InventoryResponse> getAllInventories(String productName, PageRequest pageable) {
        Specification<Inventory> spec = Specification.where(null);

        if (productName != null && !productName.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("productName")), "%" + productName.toLowerCase() + "%")
            );
        }

        Page<Inventory> resultPage = inventoryRepository.findAll(spec, pageable);
        return resultPage.map(inventoryMapper::toInventoryResponse);
    }

    @Override
    public void updateInventory(InventoryQuantityProductRequest inventoryQuantityProductRequest) {
        for (ChangeProductRequest item : inventoryQuantityProductRequest.getProducts()) {
            Inventory inventory = inventoryRepository.findByProductId(item.getProductId());
            int quantity = 0;
            quantity = inventory.getQuantity() - item.getQuantity();
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
        }
    }
}
