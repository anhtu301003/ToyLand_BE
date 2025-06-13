package com.toyland.order_service.repository.httpClient;

import com.toyland.order_service.dto.response.UserEmailProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service",url = "${app.services.user}")
public interface UserClient {
    @GetMapping(value = "/internal/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    UserEmailProfileResponse getEmailProfileUser(@PathVariable String userId);
}