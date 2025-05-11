package com.toyland.product_service.service;

import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.entity.Product;
import com.toyland.product_service.mapper.ProductMapper;
import com.toyland.product_service.repository.BrandRepository;
import com.toyland.product_service.repository.CategoryRepository;
import com.toyland.product_service.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    BrandRepository brandRepository;
    ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest) {

        Product product = productMapper.toProduct(productRequest);

        categoryRepository.save(product.getCategory());

        brandRepository.save(product.getBrand());

        try{
            productRepository.save(product);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Saved product {}", product);

        return productMapper.toProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }

    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id));
        return productMapper.toProductResponse(product);
    }

    public ProductResponse updateProduct(ProductRequest productRequest,String id) {
        Product product = productRepository.findById(id).orElse(null);

        productMapper.updateProductFromRequest(productRequest, product);

        try {
            product = productRepository.save(product);
        } catch (Exception e) {
            log.error("Error updating product: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Failed to update product: " + e.getMessage());
        }
        log.info("Updated product with id: {}", id);

        // Chuyển sang ProductResponse
        return productMapper.toProductResponse(product);
    }

    public void deleteProductById(String id) {
        // Kiểm tra xem sản phẩm có tồn tại không
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
        // Xóa sản phẩm
        productRepository.deleteById(id);
        log.info("Deleted product with id: {}", id);
    }
}
