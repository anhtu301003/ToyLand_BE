package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {OrderItemMapper.class})
public interface OrderMapper {
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "orderStatus", ignore = true)
    Order toOrder(OrderRequest orderRequest);

    OrderResponse toOrderResponse(Order order);

    void updateOrderFromRequest(OrderRequest orderRequest, @MappingTarget Order order);
}
