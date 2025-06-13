package com.toyland.order_service.service.IService;

import com.toyland.order_service.entity.Order;

public interface OrderObserver {
    void update(Order order);
}
