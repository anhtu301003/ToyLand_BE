package com.toyland.product_service.mapper;

import com.toyland.product_service.dto.request.CategoryRequest;
import com.toyland.product_service.dto.response.CategoryResponse;
import com.toyland.product_service.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toCategory(CategoryRequest categoryRequest);
    CategoryResponse toCategoryResponse(Category category);
}
