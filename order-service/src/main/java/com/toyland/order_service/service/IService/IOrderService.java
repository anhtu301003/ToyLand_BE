package com.toyland.order_service.service.IService;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;

import java.util.List;

public interface IOrderService {
//    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse updateStatusOrder(String orderId, OrderRequest orderRequest);

    String deleteOrder(String orderId);
}
