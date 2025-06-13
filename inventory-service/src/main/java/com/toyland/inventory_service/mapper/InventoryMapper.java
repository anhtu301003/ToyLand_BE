package com.toyland.inventory_service.mapper;

import com.toyland.inventory_service.dto.request.InventoryRequest;
import com.toyland.inventory_service.dto.response.InventoryResponse;
import com.toyland.inventory_service.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface InventoryMapper {
    Inventory toInventory(InventoryRequest request);
    InventoryResponse toInventoryResponse(Inventory inventory);

    void updateInventoryFromRequest(InventoryRequest request,@MappingTarget Inventory inventory);
}
