package com.toyland.product_service.service.IServices;

import com.toyland.product_service.dto.response.CategoryResponse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getAllCategories();
}
