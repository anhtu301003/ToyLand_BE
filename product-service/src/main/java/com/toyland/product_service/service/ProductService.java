package com.toyland.product_service.service;

import com.toyland.event.dto.CreateProductEvent;
import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.entity.Brand;
import com.toyland.product_service.entity.Category;
import com.toyland.product_service.entity.Product;
import com.toyland.product_service.exception.AppException;
import com.toyland.product_service.exception.ErrorCode;
import com.toyland.product_service.mapper.BrandMapper;
import com.toyland.product_service.mapper.CategoryMapper;
import com.toyland.product_service.mapper.ProductMapper;
import com.toyland.product_service.repository.BrandRepository;
import com.toyland.product_service.repository.CategoryRepository;
import com.toyland.product_service.repository.ProductRepository;
import com.toyland.product_service.service.IServices.IProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ProductService implements IProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    BrandRepository brandRepository;
    ProductMapper productMapper;
    CategoryMapper categoryMapper;
    BrandMapper brandMapper;
    KafkaTemplate<String, Object> kafkaTemplate;
    MongoTemplate mongoTemplate;

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Category category = categoryRepository.findByNameCategory(productRequest.getCategory().getNameCategory())
                .orElseGet(() -> {
                    Category newCategory = Category.builder()
                            .nameCategory(productRequest.getCategory().getNameCategory())
                            .build();
                    return categoryRepository.save(newCategory);
                });

        // Kiểm tra và lưu Brand nếu cần
        Brand brand = brandRepository.findByNameBrand(productRequest.getBrand().getNameBrand())
                .orElseGet(() -> {
                    Brand newBrand = Brand.builder()
                            .nameBrand(productRequest.getBrand().getNameBrand())
                            .build();
                    return brandRepository.save(newBrand);
                });

        Product product = productMapper.toProduct(productRequest);
        product.setCategory(category);
        product.setBrand(brand);

        Product saveProduct = productRepository.save(product);

        CreateProductEvent createProductEvent = CreateProductEvent.builder()
                .productId(product.getId())
                .productName(product.getName())
                .build();

        kafkaTemplate.send("product-events", createProductEvent);
        return productMapper.toProductResponse(saveProduct);
    }

    @Override
    public ProductResponse getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return productMapper.toProductResponse(product.get());
        } else {
            throw new AppException(ErrorCode.PRODUCT_NOT_EXIST);
        }
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, String id) {
        try {
            Product product = productRepository.findProductById(id);
            if (product == null) {
                throw new AppException(ErrorCode.PRODUCT_NOT_EXIST);
            }

            productMapper.updateProductFromRequest(productRequest, product);

            if (productRequest.getBrand() != null) {
                Brand brand = brandRepository.findByNameBrand(productRequest.getBrand().getNameBrand())
                        .orElseGet(() -> {
                            Brand newBrand = Brand.builder()
                                    .nameBrand(productRequest.getBrand().getNameBrand())
                                    .build();
                            return brandRepository.save(newBrand);
                        });
                product.setBrand(brand);
            }

            if (productRequest.getCategory() != null) {
                Category category = categoryRepository.findByNameCategory(productRequest.getCategory().getNameCategory())
                        .orElseGet(() -> {
                            Category newCategory = Category.builder()
                                    .nameCategory(productRequest.getCategory().getNameCategory())
                                    .build();
                            return categoryRepository.save(newCategory);
                        });
                product.setCategory(category);
            }
            return productMapper.toProductResponse(productRepository.save(product));
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            throw new AppException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteProductById(String id) {
        // Kiểm tra xem sản phẩm có tồn tại không
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
        // Xóa sản phẩm
        productRepository.deleteById(id);
        log.info("Deleted product with id: {}", id);
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::toProductResponse);
    }

    @Override
    public Page<ProductResponse> getAllProducts(PageRequest pageable, String nameProduct, String category, String brand) {
        Query query = new Query();

        if (nameProduct != null && !nameProduct.isEmpty()) {
            query.addCriteria(Criteria.where("name").regex(".*" + nameProduct + ".*", "i"));
        }

        if (category != null && !category.isEmpty()) {
            List<Category> categories = mongoTemplate.find(
                    Query.query(Criteria.where("nameCategory").regex(".*" + category + ".*", "i")),
                    Category.class);

            List<String> categoryIds = categories.stream().map(Category::getId).toList();
            if (!categoryIds.isEmpty()) {
                query.addCriteria(Criteria.where("category").in(categoryIds));
            } else {
                // Nếu không có category nào match thì trả về rỗng luôn
                return new PageImpl<>(List.of(), pageable, 0);
            }
        }

        if (brand != null && !brand.isEmpty()) {
            List<Brand> brands = mongoTemplate.find(
                    Query.query(Criteria.where("nameBrand").regex(".*" + brand + ".*", "i")),
                    Brand.class
            );
            List<String> brandIds = brands.stream().map(Brand::getId).toList();
            if (!brandIds.isEmpty()) {
                query.addCriteria(Criteria.where("brand").in(brandIds));
            } else {
                return new PageImpl<>(List.of(), pageable, 0);
            }
        }


        long total = mongoTemplate.count(query, Product.class);

        query.with(pageable);

        List<Product> products = mongoTemplate.find(query, Product.class);

        List<ProductResponse> responses = products.stream().map(productMapper::toProductResponse).toList();

        return new PageImpl<>(responses, pageable, total);
    }
}
