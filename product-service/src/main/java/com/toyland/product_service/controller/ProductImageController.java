package com.toyland.product_service.controller;

import com.toyland.product_service.dto.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class ProductImageController {
    @PostMapping("/{productId}/images")
    public ApiResponse<String> uploadImage(@PathVariable("productId") String productId, @RequestParam MultipartFile file) {
        return null;
    }

    @DeleteMapping("/{productId}/images/{imageId}")
    public ApiResponse<String> deleteImage(@PathVariable("productId") String productId, @PathVariable("imageId") String imageId, @RequestParam MultipartFile file) {
        return null;
    }
}
