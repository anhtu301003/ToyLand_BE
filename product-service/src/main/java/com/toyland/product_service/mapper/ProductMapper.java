package com.toyland.product_service.mapper;

import com.toyland.product_service.dto.request.ProductRequest;
import com.toyland.product_service.dto.response.ProductResponse;
import com.toyland.product_service.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
     Product toProduct(ProductRequest productRequest);

     @Mapping(source = "id",target = "id")
     ProductResponse toProductResponse(Product product);

     void updateProductFromRequest(ProductRequest productRequest,@MappingTarget Product product);
}
