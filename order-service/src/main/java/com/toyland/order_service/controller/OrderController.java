package com.toyland.order_service.controller;

import com.toyland.order_service.dto.ApiResponse;
import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;

    @PostMapping
    public ApiResponse<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(orderRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<OrderResponse>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ApiResponse.<Page<OrderResponse>>builder()
                .result(orderService.getAllOrders(PageRequest.of(page - 1, size)))
                .build();
    }

    @PutMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateStatusOrder(@PathVariable String orderId, @RequestBody OrderRequest orderRequest) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateStatusOrder(orderId, orderRequest))
                .build();
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<String> deleteOrder(@PathVariable String orderId) {
        return ApiResponse.<String>builder()
                .result(orderService.deleteOrder(orderId))
                .build();
    }

}
