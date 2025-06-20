package com.toyland.user_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressRequest {
    String phoneNumber;
    String province;
    String street;
    String country;
    String postalCode;
    boolean isDefault;
}
