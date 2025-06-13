package com.toyland.user_service.dto.response;

import com.toyland.user_service.entity.UserProfile;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAddressResponse {
    String id;
    String phoneNumber;
    String city;
    String state;
    String country;
    String postalCode;
    Boolean isDefault;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String userId;
}
