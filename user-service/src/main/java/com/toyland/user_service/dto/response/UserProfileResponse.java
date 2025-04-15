package com.toyland.user_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {
     String userId;
     String email;
     String fullName;
     String avatarUrl;
     LocalDate birthDay;
     String gender;

}
