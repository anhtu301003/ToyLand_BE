package com.toyland.inventory_service.controller;

import com.toyland.event.dto.InventoryQuantityProductRequest;
import com.toyland.inventory_service.service.InventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryKafkaListener {
    InventoryService inventoryService;

    @KafkaListener(topics = "inventory-delivery")
    public void CreateUserDelivery(InventoryQuantityProductRequest event) {
        log.info("Received CreateUserEvent: {}", event);
        inventoryService.updateInventory(event);
    }
}
