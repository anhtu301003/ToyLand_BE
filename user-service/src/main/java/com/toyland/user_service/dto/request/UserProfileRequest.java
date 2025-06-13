package com.toyland.user_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileRequest {
    String email;
    String fullName;
    String avatarUrl;
    LocalDate birthDay;
    String gender;
    String phoneNumber;
    Boolean admin;
}
