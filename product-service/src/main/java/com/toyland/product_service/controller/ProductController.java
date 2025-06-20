package com.toyland.product_service.controller;

import com.toyland.product_service.dto.ApiResponse;
import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.request.UpdateTrendingRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/products")
@Slf4j
public class ProductController {
    ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.createProduct(productRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<Page<ProductResponse>> getAllProducts(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.<Page<ProductResponse>>builder()
                .result(productService.getAllProducts(PageRequest.of(page - 1, size)))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProductById(@RequestBody ProductRequest productRequest, @PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productRequest, id))
                .build();
    }

    @PatchMapping("/{productId}/trending")
    public ApiResponse<ProductResponse> updateProductTrending(@RequestBody UpdateTrendingRequest updateTrendingRequest, @PathVariable String productId) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProductTrending(updateTrendingRequest, productId))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
        return ApiResponse.<Void>builder()
                .message("Product deleted successfully")
                .build();
    }
}
