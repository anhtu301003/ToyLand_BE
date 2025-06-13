package com.toyland.inventory_service.service;

import com.toyland.event.dto.CreateProductEvent;
import com.toyland.inventory_service.entity.Inventory;
import com.toyland.inventory_service.mapper.InventoryMapper;
import com.toyland.inventory_service.repository.InventoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductEventListener {
    InventoryRepository inventoryRepository;
    InventoryMapper inventoryMapper;
    LowStockNotificationService lowStockNotificationService;
    @KafkaListener(topics = "product-events",groupId = "inventory-group")
    public void handleProductCreated(CreateProductEvent productEvent) {
        log.info("Handling product event: {}", productEvent);

        Inventory inventory = Inventory.builder()
                .productId(productEvent.getProductId())
                .productName(productEvent.getProductName())
                .quantity(-1)
                .lowStockThreshold(5)
                .build();
        lowStockNotificationService.checkAndNotifyLowStock(inventory);
        inventoryRepository.save(inventory);
        log.info("Inventory saved: {}", inventory);
    }
}
