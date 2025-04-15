package com.toyland.user_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressResponse {
    String phoneNumber;
    String city;
    String state;
    String country;
    String postalCode;
    Boolean isDefault;
}
