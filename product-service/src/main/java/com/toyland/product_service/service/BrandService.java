package com.toyland.product_service.service;

import com.toyland.product_service.dto.response.BrandResponse;
import com.toyland.product_service.mapper.BrandMapper;
import com.toyland.product_service.repository.BrandRepository;
import com.toyland.product_service.service.IServices.IBrandService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BrandService implements IBrandService {

    BrandRepository brandRepository;
    BrandMapper brandMapper;

    @Override
    public List<BrandResponse> getAllBrands() {
        return brandRepository.findAll().stream().map(brandMapper::toBrandResponse).toList();
    }
}
