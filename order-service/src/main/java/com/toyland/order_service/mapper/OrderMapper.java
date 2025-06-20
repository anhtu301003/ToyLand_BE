package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {OrderItemMapper.class, AddressOrderMapper.class, UserOrderMapper.class})
public interface OrderMapper {
    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderItems", source = "orderItems")
    @Mapping(target = "userOrder", source = "userOrder")
    @Mapping(target = "addressOrder", source = "addressOrder")
    Order toOrder(OrderRequest orderRequest);

    @Mapping(target = "userOrder", source = "userOrder")
    @Mapping(target = "addressOrder", source = "addressOrder")
    OrderResponse toOrderResponse(Order order);

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "userOrder", ignore = true)
    @Mapping(target = "addressOrder", ignore = true)
    void updateOrderFromRequest(OrderRequest orderRequest, @MappingTarget Order order);
}
