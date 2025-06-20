package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.UserOrderRequest;
import com.toyland.order_service.dto.response.UserOrderResponse;
import com.toyland.order_service.entity.UserOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserOrderMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserOrder toUserOrder(UserOrderRequest userOrderRequest);

    UserOrderResponse toUserOrderResponse(UserOrder userOrder);
}
