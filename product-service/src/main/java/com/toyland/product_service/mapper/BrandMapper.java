package com.toyland.product_service.mapper;

import com.toyland.product_service.dto.request.BrandRequest;
import com.toyland.product_service.dto.response.BrandResponse;
import com.toyland.product_service.entity.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    Brand toBrand(BrandRequest brandRequest);
    BrandResponse toBrandResponse(Brand brand);
}
