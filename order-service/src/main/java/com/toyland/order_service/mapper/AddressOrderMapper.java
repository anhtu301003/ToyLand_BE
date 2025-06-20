package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.AddressOrderRequest;
import com.toyland.order_service.dto.response.AddressOrderResponse;
import com.toyland.order_service.entity.AddressOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressOrderMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AddressOrder toAddressOrder(AddressOrderRequest addressOrderRequest);

    AddressOrderResponse toAddressOrderResponse(AddressOrder addressOrder);
}
