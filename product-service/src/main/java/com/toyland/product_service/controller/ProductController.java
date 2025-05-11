package com.toyland.product_service.controller;

import com.toyland.product_service.dto.ApiResponse;
import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
    public ApiResponse<List<ProductResponse>> getAllProducts() {
        return ApiResponse.<List<ProductResponse>>builder()
                .result(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getProductById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse> updateProductById(@RequestBody ProductRequest productRequest,@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.updateProduct(productRequest,id))
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
