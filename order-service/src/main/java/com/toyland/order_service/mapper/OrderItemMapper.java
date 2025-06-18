package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.OrderItemRequest;
import com.toyland.order_service.dto.response.OrderItemResponse;
import com.toyland.order_service.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderItemMapper {
    @Mapping(target = "orderItemId", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);

    @Mapping(target = "orderId", source = "order.orderId")
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
