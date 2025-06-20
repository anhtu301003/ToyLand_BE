package com.toyland.product_service.service.IServices;

import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.request.UpdateTrendingRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(String id);

    ProductResponse updateProduct(ProductRequest productRequest, String id);

    void deleteProductById(String id);

    Page<ProductResponse> getAllProducts(Pageable pageable);

    Page<ProductResponse> getAllProducts(PageRequest pageable, String nameProduct, String category, String brand, Boolean trending);

    ProductResponse updateProductTrending(UpdateTrendingRequest updateTrendingRequest, String productId);
}
