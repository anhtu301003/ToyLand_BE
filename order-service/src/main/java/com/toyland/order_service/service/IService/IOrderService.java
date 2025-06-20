package com.toyland.order_service.service.IService;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.request.UpdateStatusOrder;
import com.toyland.order_service.dto.response.OrderResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
//    OrderResponse createOrder(OrderRequest orderRequest);

    @Transactional()
    OrderResponse createOrder(OrderRequest orderRequest);

    Page<OrderResponse> getAllOrders(Pageable pageable, String search, String status);

    OrderResponse updateStatusOrder(String orderId, UpdateStatusOrder updateStatusOrder);

    String deleteOrder(String orderId);

    Page<OrderResponse> getAllOrdersByUserId(PageRequest pageable, String userId);
}
