package com.toyland.order_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressOrderRequest {
    String street;
    String province;
    String postalCode;
    String country;
}
