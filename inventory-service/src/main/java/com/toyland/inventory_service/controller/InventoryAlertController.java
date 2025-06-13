package com.toyland.inventory_service.controller;

import com.toyland.inventory_service.dto.ApiResponse;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InventoryAlertController {
    @GetMapping("low-stock")
    public ApiResponse<List<InventoryResponse>> lowStockAlert() {
        return null;
    }

    @PostMapping()
    public ApiResponse<InventoryResponse> createAlert() {
        return null;
    }

    @GetMapping()
    public ApiResponse<List<InventoryResponse>> getAlerts() {
        return null;
    }

    @PutMapping("/{alertId}")
    public ApiResponse<List<InventoryResponse>> updateAlertById(@PathVariable("alertId") String alertId) {
        return null;
    }

    @DeleteMapping("/{alertId}")
    public ApiResponse<String> deleteAlertById(@PathVariable("alertId") String alertId) {
        return null;
    }
}
