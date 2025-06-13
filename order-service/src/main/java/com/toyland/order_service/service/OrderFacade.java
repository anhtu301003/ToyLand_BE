package com.toyland.order_service.service;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.dto.response.UserEmailProfileResponse;
import com.toyland.order_service.entity.Order;
import com.toyland.order_service.entity.OrderItem;
import com.toyland.order_service.mapper.OrderItemMapper;
import com.toyland.order_service.mapper.OrderMapper;
import com.toyland.order_service.repository.OrderItemRepository;
import com.toyland.order_service.repository.OrderRepository;
import com.toyland.order_service.repository.httpClient.UserClient;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderFacade {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final KafkaTemplate<String,Object> kafkaTemplate;
    private final UserClient userClient;


    public OrderFacade(OrderRepository orderRepository, OrderItemRepository orderItemRepository,OrderMapper orderMapper,OrderItemMapper orderItemMapper,KafkaTemplate<String,Object> kafkaTemplate,UserClient userClient) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.kafkaTemplate = kafkaTemplate;
        this.userClient = userClient;
    }

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest) {
        try{
            Order order = orderMapper.toOrder(orderRequest);
            Order savedOrder = orderRepository.save(order);

            UserEmailProfileResponse userEmail = userClient.getEmailProfileUser(orderRequest.getUserId());
            //Đăng ký observer
            order.registerObserver(new KafkaNotificationObserver(kafkaTemplate,userEmail.getEmail()));
            // Map OrderItems với orderId từ savedOrder
            List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
                    .map(orderItemRequest -> orderItemMapper.toOrderItem(orderItemRequest, savedOrder.getOrderId()))
                    .toList();
            // Lưu OrderItems
            List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);
            // Tạo response
            OrderResponse orderResponse = orderMapper.toOrderResponse(savedOrder);
            orderResponse.setOrderItems(savedOrderItems.stream()
                    .map(orderItemMapper::toOrderItemResponse)
                    .toList());
            return orderResponse;
        }
        catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
