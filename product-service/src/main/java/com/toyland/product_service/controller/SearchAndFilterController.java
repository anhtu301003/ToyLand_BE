package com.toyland.product_service.controller;

import com.toyland.product_service.dto.ApiResponse;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class SearchAndFilterController {
    ProductService productService;

    @GetMapping("/search")
    public ApiResponse<Page<ProductResponse>> searchProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String nameProduct,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Boolean trending
    ) {
        return ApiResponse.<Page<ProductResponse>>builder()
                .result(productService.getAllProducts(PageRequest.of(page - 1, size), nameProduct, category, brand, trending))
                .build();
    }

    @GetMapping("/filter")
    public ApiResponse<List<ProductResponse>> filterProducts(@RequestParam String query) {
        return null;
    }

    @GetMapping("/categories/{category}")
    public ApiResponse<Page<ProductResponse>> getProductsByCategory(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String category) {
        return ApiResponse.<Page<ProductResponse>>builder()
                .result(productService.getAllProducts(PageRequest.of(page - 1, size), "", category, "", null))
                .build();
    }

    @GetMapping("/featured")
    public ApiResponse<List<ProductResponse>> getFeaturedProducts(@RequestParam String query) {
        return null;
    }

    @GetMapping("/best-sellers")
    public ApiResponse<List<ProductResponse>> getBestSellers(@RequestParam String query) {
        return null;
    }
}
