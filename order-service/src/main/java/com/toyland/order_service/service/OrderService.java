package com.toyland.order_service.service;

import com.toyland.order_service.Enum.OrderStatusEnum;
import com.toyland.event.dto.ChangeProductRequest;
import com.toyland.event.dto.InventoryQuantityProductRequest;
import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.request.UpdateStatusOrder;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.entity.Order;
import com.toyland.order_service.entity.OrderItem;
import com.toyland.order_service.exception.AppException;
import com.toyland.order_service.exception.ErrorCode;
import com.toyland.order_service.mapper.OrderItemMapper;
import com.toyland.order_service.mapper.OrderMapper;
import com.toyland.order_service.repository.OrderItemRepository;
import com.toyland.order_service.repository.OrderRepository;
import com.toyland.order_service.repository.httpClient.InventoryClient;
import com.toyland.order_service.repository.httpClient.UserClient;
import com.toyland.order_service.service.IService.IOrderService;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    KafkaTemplate<String, Object> kafkaTemplate;
    UserClient userClient;
    InventoryClient inventoryClient;

    @Transactional
    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {
        try {
            Order order = orderMapper.toOrder(orderRequest);

            List<OrderItem> items = order.getOrderItems();

            // Danh sách yêu cầu thay đổi tồn kho
            List<ChangeProductRequest> changeProductRequests = new ArrayList<>();

            // Gán quan hệ và tạo danh sách thay đổi sản phẩm
            items.forEach(item -> {
                item.setOrder(order);
                changeProductRequests.add(
                        new ChangeProductRequest(item.getProductId(), item.getQuantity())
                );
            });

            InventoryQuantityProductRequest quantityProductRequest = InventoryQuantityProductRequest.builder()
                    .products(changeProductRequests)
                    .build();
//            try {
//                inventoryClient.updateQuantityProduct(quantityProductRequest);
//            } catch (Exception e) {
//                log.error(e.getMessage());
//            }

            kafkaTemplate.send("inventory-delivery", quantityProductRequest);
            return orderMapper.toOrderResponse(orderRepository.save(order));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

    public Page<OrderResponse> getAllOrders(Pageable pageable, String search, String status) {
        // Xây dựng specification để lọc động
        Specification<Order> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // 1. Logic tìm kiếm (SEARCH)
            // Tìm kiếm trên nhiều trường: mã đơn hàng, tên người nhận, tên người đặt hàng
            if (search != null && !search.trim().isEmpty()) {
                String searchTerm = "%" + search.toLowerCase().trim() + "%";

                // Join với bảng User để tìm theo tên người đặt
                Join<Object, Object> userJoin = root.join("userOrder");

                // Tạo các điều kiện OR cho các trường cần tìm kiếm
                Predicate searchPredicate = cb.or(
                        // Tìm theo mã đơn hàng (orderId)
                        cb.like(cb.lower(root.get("orderId")), searchTerm),
                        // Tìm theo tên người nhận (giả sử trường là 'fullName' trong Order)
                        cb.like(cb.lower(root.get("fullName")), searchTerm),
                        // Tìm theo tên người đặt hàng (giả sử trường là 'fullName' trong User)
                        cb.like(cb.lower(userJoin.get("fullName")), searchTerm)
                );
                predicates.add(searchPredicate);
            }

            // 2. Logic lọc theo trạng thái (STATUS)
            if (status != null && !status.isEmpty()) {
                try {
                    OrderStatusEnum statusEnum = OrderStatusEnum.valueOf(status.toUpperCase());
                    predicates.add(cb.equal(root.get("orderStatus"), statusEnum));
                } catch (IllegalArgumentException e) {
                    // Nếu status không hợp lệ, ném ra lỗi
                    throw new AppException(ErrorCode.INVALID_ORDER_STATUS);
                }
            }

            // Kết hợp tất cả các điều kiện bằng AND
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<Order> resultPage = orderRepository.findAll(spec, pageable);
        return resultPage.map(orderMapper::toOrderResponse);
    }

    @Override
    @Transactional
    public OrderResponse updateStatusOrder(String orderId, UpdateStatusOrder updateStatusOrder) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXIST));
        if (order.getObservers().isEmpty()) {
            order.registerObserver(new KafkaNotificationObserver(kafkaTemplate, order.getUserOrder().getEmail()));
        }

        order.setOrderStatus(OrderStatusEnum.valueOf(updateStatusOrder.getOrderStatus()));

        return orderMapper.toOrderResponse(orderRepository.save(order));
    }


    @Override
    public String deleteOrder(String orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
            orderRepository.delete(order.get());
            return "delete order success " + orderId;
        } else {
            throw new AppException(ErrorCode.ORDER_NOT_EXIST);
        }
    }

    @Override
    public Page<OrderResponse> getAllOrdersByUserId(PageRequest pageable, String userId) {
        Specification<Order> spec = Specification.where(null);
        if (userId != null && !userId.isEmpty()) {
            spec = spec.and((root, query, cb) -> {
                Join<Object, Object> userJoin = root.join("userOrder");
                return cb.like(cb.lower(userJoin.get("userId")), "%" + userId.toLowerCase() + "%");
            });
        }
        // Lọc theo status (OrderStatusEnum)

        Page<Order> resultPage = orderRepository.findAll(spec, pageable);
        return resultPage.map(orderMapper::toOrderResponse);
    }
}
