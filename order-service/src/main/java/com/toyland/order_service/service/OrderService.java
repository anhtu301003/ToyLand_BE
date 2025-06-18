package com.toyland.order_service.service;

import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.entity.Order;
import com.toyland.order_service.entity.OrderItem;
import com.toyland.order_service.mapper.OrderItemMapper;
import com.toyland.order_service.mapper.OrderMapper;
import com.toyland.order_service.repository.OrderItemRepository;
import com.toyland.order_service.repository.OrderRepository;
import com.toyland.order_service.repository.httpClient.UserClient;
import com.toyland.order_service.service.IService.IOrderService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderMapper orderMapper;
    OrderItemMapper orderItemMapper;
    KafkaTemplate<String, Object> kafkaTemplate;
    UserClient userClient;

    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        try {
            Order order = orderMapper.toOrder(orderRequest);

//            UserEmailProfileResponse userEmail = userClient.getEmailProfileUser(orderRequest.getUserId());
//            //Đăng ký observer
//            order.registerObserver(new KafkaNotificationObserver(kafkaTemplate, userEmail.getEmail()));
            // Map OrderItems với orderId từ savedOrder

            List<OrderItem> items = order.getOrderItems();

            for (OrderItem orderItem : items) {
                orderItem.setOrder(order);
            }

            return orderMapper.toOrderResponse(orderRepository.save(order));
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        List<Order> orders = orderRepository.findAll(pageable).getContent();
        int total = orders.size();
        List<OrderResponse> orderResponses = orders.stream().map(orderMapper::toOrderResponse).toList();
        return new PageImpl<>(orderResponses, pageable, total);
    }

    @Override
    public OrderResponse updateStatusOrder(String orderId, OrderRequest orderRequest) {
//        Order order = orderRepository.findById(orderId).orElse(null);
//
//        UserEmailProfileResponse userEmail = userClient.getEmailProfileUser(order.getUserId());
//        System.out.println(userEmail.getEmail());
//        if (order.getObservers().isEmpty()) {
//            order.registerObserver(new KafkaNotificationObserver(kafkaTemplate, userEmail.getEmail()));
//        }
//        orderMapper.updateOrderFromRequest(orderRequest, order);
//        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
//
//
//        OrderResponse orderResponse = orderMapper.toOrderResponse(orderRepository.save(order));
//        orderResponse.setOrderItems(orderItems.stream().map(orderItemMapper::toOrderItemResponse).toList());
//        return orderResponse;
        return null;
    }


    @Override
    public String deleteOrder(String orderId) {
//        Optional<Order> order = orderRepository.findById(orderId);
//        if (order.isPresent()) {
//            try {
//                orderRepository.delete(order.get());
//                orderItemRepository.deleteAllByOrderId(orderId);
//            } catch (Exception e) {
//                return "Delete Failure " + e.getMessage();
//            }
//        } else {
//            return "Order is not exist " + orderId;
//        }

        return "Delete Order Successfully";
    }


}
