package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.OrderItemRequest;
import com.toyland.order_service.dto.response.OrderItemResponse;
import com.toyland.order_service.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem toOrderItem(OrderItemRequest orderItemRequest);
    // Map từ OrderItemRequest sang OrderItem với orderId được set
    @Mapping(target = "orderId", source = "orderId")
    OrderItem toOrderItem(OrderItemRequest orderItemRequest, String orderId);
    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}
