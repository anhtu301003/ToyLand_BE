package com.toyland.inventory_service.controller;

import com.toyland.inventory_service.dto.ApiResponse;
import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.request.InventoryTransactionRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import com.toyland.inventory_service.dto.response.InventoryTransactionResponse;
import com.toyland.inventory_service.service.InventoryService;
import com.toyland.inventory_service.service.InventoryTransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryHistory {

    InventoryTransactionService inventoryTransactionService;

    @GetMapping("/{productId}/history")
    public ApiResponse<InventoryTransactionResponse> getInventoryHistory(@PathVariable String productId) {
        return null;
    }

    @GetMapping("/movements")
    public ApiResponse<InventoryTransactionResponse> getInventoryMovement() {
        return null;
    }
}
