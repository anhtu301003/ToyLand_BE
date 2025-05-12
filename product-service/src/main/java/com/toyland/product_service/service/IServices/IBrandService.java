package com.toyland.product_service.service.IServices;

import com.toyland.product_service.dto.response.BrandResponse;

import java.util.List;

public interface IBrandService {
    List<BrandResponse> getAllBrands();
}
