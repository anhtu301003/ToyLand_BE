package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    Order toOrder(OrderRequest orderRequest);
    OrderResponse toOrderResponse(Order order);

    void updateOrderFromRequest(OrderRequest orderRequest,@MappingTarget Order order);
}
