package com.toyland.order_service.entity;

import com.toyland.order_service.Enum.OrderStatusEnum;
import com.toyland.order_service.service.IService.OrderObserver;
import com.toyland.order_service.service.IService.Subject;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "orders",
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id"),
                @Index(name = "idx_order_status", columnList = "order_status")
        })
@EntityListeners(AuditingEntityListener.class)
public class Order implements Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String orderId;

    String userId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    OrderStatusEnum orderStatus = OrderStatusEnum.PENDING;

    int totalPrice;

    int totalQuantity;

    String shippingAddress;

    String paymentId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    List<OrderItem> orderItems = new ArrayList<>();

    @CreatedDate
    LocalDateTime createdAt;

    @LastModifiedDate
    LocalDateTime updatedAt;

    @Transient
    List<OrderObserver> observers = new ArrayList<>();

    @Override
    public void registerObserver(OrderObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public void setOrderStatus(OrderStatusEnum newStatus) {
        if (this.orderStatus != newStatus) {
            this.orderStatus = newStatus;
            notifyObservers();
        }
    }
}
