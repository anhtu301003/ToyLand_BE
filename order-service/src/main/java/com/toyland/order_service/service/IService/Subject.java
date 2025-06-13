package com.toyland.order_service.service.IService;

public interface Subject {
    void registerObserver(OrderObserver observer);
    void removeObserver(OrderObserver observer);
    void notifyObservers();
}
