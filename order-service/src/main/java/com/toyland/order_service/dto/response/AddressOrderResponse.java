package com.toyland.order_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressOrderResponse {
    String addressId;
    String street;
    String province;
    String postalCode;
    String country;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
