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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(required = false) String brand) {
        return ApiResponse.<Page<ProductResponse>>builder()
                .result(productService.getAllProducts(PageRequest.of(page - 1, size), nameProduct, category, brand))
                .build();
    }

    @GetMapping("/filter")
    public ApiResponse<List<ProductResponse>> filterProducts(@RequestParam String query) {
        return null;
    }

    @GetMapping("/categories/{categoryId}")
    public ApiResponse<List<ProductResponse>> getProductsByCategory(@RequestParam String query) {
        return null;
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
