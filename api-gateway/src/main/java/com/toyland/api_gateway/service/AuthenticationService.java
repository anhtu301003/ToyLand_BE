package com.toyland.api_gateway.service;

import com.toyland.api_gateway.dto.request.IntrospectRequest;
import com.toyland.api_gateway.dto.response.ApiResponse;
import com.toyland.api_gateway.dto.response.IntrospectResponse;
import com.toyland.api_gateway.repository.AuthenticationClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    AuthenticationClient authenticationClient;

    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return authenticationClient.introspect(IntrospectRequest.builder()
                .token(token)
                .build());
    }
}
