package com.toyland.product_service.mapper;

import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, uses = {BrandMapper.class, CategoryMapper.class})
public interface ProductMapper {
    @Mapping(target = "id", ignore = true) // Bỏ qua id vì nó sẽ được tạo tự động
    @Mapping(target = "createdAt", ignore = true) // Bỏ qua createdAt vì được tạo bởi @CreatedDate
    @Mapping(target = "updatedAt", ignore = true) // Bỏ qua updatedAt vì được tạo bởi @LastModifiedDate
    @Mapping(target = "category", source = "category", qualifiedByName = "toCategory")
    @Mapping(target = "brand", source = "brand", qualifiedByName = "toBrand")
    Product toProduct(ProductRequest productRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "category", source = "category", qualifiedByName = "toCategoryResponse")
    @Mapping(target = "brand", source = "brand", qualifiedByName = "toBrandResponse")
    ProductResponse toProductResponse(Product product);

    void updateProductFromRequest(ProductRequest productRequest, @MappingTarget Product product);
}
