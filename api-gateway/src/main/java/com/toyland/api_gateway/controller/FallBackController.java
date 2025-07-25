package com.toyland.api_gateway.controller;

import com.toyland.api_gateway.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {
    @GetMapping("/fallback")
    public ResponseEntity<ApiResponse<Object>> fallback() {
        ApiResponse<Object> response = ApiResponse.builder()
                .code(503)
                .result(null)
                .message("Service is temporarily unavailable. Please try again later.")
                .build();

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
    }
}
