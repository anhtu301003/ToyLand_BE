package com.toyland.order_service.repository.httpClient;

import com.toyland.event.dto.InventoryQuantityProductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "inventory-service", url = "${app.services.inventory}")
public interface InventoryClient {
    @PutMapping(value = "/internal/inventory", produces = MediaType.APPLICATION_JSON_VALUE)
    void updateQuantityProduct(@RequestBody InventoryQuantityProductRequest inventoryQuantityProductRequest);
}

