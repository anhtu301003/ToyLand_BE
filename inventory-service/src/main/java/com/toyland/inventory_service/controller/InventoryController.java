package com.toyland.inventory_service.controller;

import com.toyland.inventory_service.dto.ApiResponse;
import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import com.toyland.inventory_service.service.InventoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryController {

    InventoryService inventoryService;

    @PostMapping
    public ApiResponse<InventoryResponse> CreateInventory(@RequestBody InventoryRequest request) {
        return ApiResponse.<InventoryResponse>builder()
                .result(inventoryService.createInventory(request))
                .build();
    }

    @GetMapping("/{productId}")
    public ApiResponse<List<InventoryResponse>> GetInventoryByProductId(@PathVariable String productId) {
        return ApiResponse.<List<InventoryResponse>>builder()
                .result(inventoryService.getInventoryByProductId(productId))
                .build();
    }

    @GetMapping()
    public ApiResponse<Page<InventoryResponse>> GetAllInventories(
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<InventoryResponse>>builder()
                .result(inventoryService.getAllInventories(productName, PageRequest.of(page - 1, size)))
                .build();
    }

    @PutMapping("/{inventoryId}")
    public ApiResponse<InventoryResponse> UpdateInventory(@PathVariable String inventoryId, @RequestBody InventoryRequest request) {
        return ApiResponse.<InventoryResponse>builder()
                .result(inventoryService.updateInventory(inventoryId, request))
                .build();
    }

    @DeleteMapping("/{inventoryId}")
    public ApiResponse<String> DeleteInventory(@PathVariable String inventoryId) {
        return ApiResponse.<String>builder()
                .result(inventoryService.deleteInventory(inventoryId))
                .build();
    }
}
