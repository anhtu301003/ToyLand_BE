package com.toyland.product_service.mapper;

import com.toyland.product_service.dto.request.BrandRequest;
import com.toyland.product_service.dto.response.BrandResponse;
import com.toyland.product_service.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    @Mapping(target = "id", ignore = true)
    @Named("toBrand")
    Brand toBrand(BrandRequest brandRequest);

    @Named("toBrandResponse")
    BrandResponse toBrandResponse(Brand brand);
}
