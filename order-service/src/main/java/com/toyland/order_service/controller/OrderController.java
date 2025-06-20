package com.toyland.order_service.controller;

import com.toyland.order_service.dto.ApiResponse;
import com.toyland.order_service.dto.request.OrderRequest;
import com.toyland.order_service.dto.request.UpdateStatusOrder;
import com.toyland.order_service.dto.response.OrderResponse;
import com.toyland.order_service.service.OrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status
    ) {
        int pageNumber = Math.max(page, 1);
        return ApiResponse.<Page<OrderResponse>>builder()
                .result(orderService.getAllOrders(PageRequest.of(pageNumber - 1, size), search, status))
                .build();
    }

    @GetMapping("/user")
    public ApiResponse<Page<OrderResponse>> getAllOrdersByUserId(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = jwt.getClaims().get("userId").toString();
        int pageNumber = Math.max(page, 1);
        return ApiResponse.<Page<OrderResponse>>builder()
                .result(orderService.getAllOrdersByUserId(PageRequest.of(pageNumber - 1, size), userId))
                .build();
    }

    @PatchMapping("/{orderId}")
    public ApiResponse<OrderResponse> updateStatusOrder(@PathVariable String orderId, @RequestBody UpdateStatusOrder updateStatusOrder) {
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.updateStatusOrder(orderId, updateStatusOrder))
                .build();
    }

    @DeleteMapping("/{orderId}")
    public ApiResponse<String> deleteOrder(@PathVariable String orderId) {
        return ApiResponse.<String>builder()
                .result(orderService.deleteOrder(orderId))
                .build();
    }

}
