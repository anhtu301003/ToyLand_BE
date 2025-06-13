package com.toyland.product_service.mapper;

import com.toyland.product_service.dto.request.CategoryRequest;
import com.toyland.product_service.dto.response.CategoryResponse;
import com.toyland.product_service.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    @Named("toCategory")
    Category toCategory(CategoryRequest categoryRequest);

    @Named("toCategoryResponse")
    CategoryResponse toCategoryResponse(Category category);
}
