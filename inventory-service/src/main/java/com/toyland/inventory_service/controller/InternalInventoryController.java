package com.toyland.inventory_service.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalInventoryController {

//    InventoryService inventoryService;
//
//    @PutMapping(value = "/internal/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
//    void updateQuantityProduct(@RequestBody InventoryQuantityProductRequest inventoryQuantityProductRequest) {
//        inventoryService.updateInventory(inventoryQuantityProductRequest);
//    }
}
