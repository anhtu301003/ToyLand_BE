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
import com.toyland.order_service.service.IService.IOrderService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService implements IOrderService {
    OrderRepository orderRepository;
    OrderItemRepository orderItemRepository;
    OrderMapper orderMapper;
    OrderItemMapper orderItemMapper;
    KafkaTemplate<String,Object> kafkaTemplate;
    UserClient userClient;

//    @Override
//    @Transactional()
//    public OrderResponse createOrder(OrderRequest orderRequest) {
//        try{
//            Order order = orderMapper.toOrder(orderRequest);
//            Order savedOrder = orderRepository.save(order);
//
//            UserEmailProfileResponse userEmail = userClient.getEmailProfileUser(orderRequest.getUserId());
//            //Đăng ký observer
//            order.registerObserver(new KafkaNotificationObserver(kafkaTemplate,userEmail.getEmail()));
//            // Map OrderItems với orderId từ savedOrder
//            List<OrderItem> orderItems = orderRequest.getOrderItems().stream()
//                    .map(orderItemRequest -> orderItemMapper.toOrderItem(orderItemRequest, savedOrder.getOrderId()))
//                    .toList();
//            // Lưu OrderItems
//            List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);
//            // Tạo response
//            OrderResponse orderResponse = orderMapper.toOrderResponse(savedOrder);
//            orderResponse.setOrderItems(savedOrderItems.stream()
//                    .map(orderItemMapper::toOrderItemResponse)
//                    .toList());
//            return orderResponse;
//            }
//            catch (Exception e){
//                log.error(e.getMessage());
//                return null;
//            }
//    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponses = new ArrayList<>();

        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(order.getOrderId());

            OrderResponse orderResponse = orderMapper.toOrderResponse(order);
            orderResponse.setOrderItems(orderItems.stream().map(orderItemMapper::toOrderItemResponse).toList());
            orderResponses.add(orderResponse);
        }

        return orderResponses;
    }

    @Override
    public OrderResponse updateStatusOrder(String orderId, OrderRequest orderRequest) {
        Order order = orderRepository.findById(orderId).orElse(null);

        UserEmailProfileResponse userEmail = userClient.getEmailProfileUser(order.getUserId());
        System.out.println(userEmail.getEmail());
        if(order.getObservers().isEmpty()) {
            order.registerObserver(new KafkaNotificationObserver(kafkaTemplate,userEmail.getEmail()));
        }
        orderMapper.updateOrderFromRequest(orderRequest, order);
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);


        OrderResponse orderResponse = orderMapper.toOrderResponse(orderRepository.save(order));
        orderResponse.setOrderItems(orderItems.stream().map(orderItemMapper::toOrderItemResponse).toList());
        return orderResponse;
    }


    @Override
    public String deleteOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            try{
                orderRepository.delete(order.get());
                orderItemRepository.deleteAllByOrderId(orderId);
            }catch (Exception e) {
                return "Delete Failure "+e.getMessage();
            }
        }
        else{
            return "Order is not exist "+orderId;
        }

        return "Delete Order Successfully";
    }


}
