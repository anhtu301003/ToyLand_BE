package com.toyland.user_service.dto.response;

import com.toyland.user_service.entity.UserAddress;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
     String phoneNumber;
     Boolean admin;
     List<UserAddressResponse> addresses;
     LocalDateTime createdAt;
     LocalDateTime updatedAt;
}
