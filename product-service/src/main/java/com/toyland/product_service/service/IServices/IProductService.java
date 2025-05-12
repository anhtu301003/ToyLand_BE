package com.toyland.product_service.service.IServices;

import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.response.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(String id);

    ProductResponse updateProduct(ProductRequest productRequest, String id);

    void deleteProductById(String id);
}
