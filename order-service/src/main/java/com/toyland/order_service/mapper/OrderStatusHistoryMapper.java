package com.toyland.order_service.mapper;

import com.toyland.order_service.dto.request.OrderStatusHistoryRequest;
import com.toyland.order_service.dto.response.OrderStatusHistoryResponse;
import com.toyland.order_service.entity.OrderStatusHistory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderStatusHistoryMapper {
    OrderStatusHistory toOrderStatusHistory(OrderStatusHistoryRequest orderStatusHistoryRequest);
    OrderStatusHistoryResponse toOrderStatusHistoryResponse(OrderStatusHistory orderStatusHistory);
}
