package com.toyland.user_service.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "user_profile",
        indexes = {
                @Index(name = "idx_fullName", columnList = "full_name"),
                @Index(name = "idx_email", columnList = "email")
        }
)
public class UserProfile {
    @Id
    String userId;
    String email;
    String fullName;
    String avatarUrl;
    LocalDate birthDay;
    String phoneNumber;
    String gender;

    Boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserAddress> addresses;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime updatedAt;
}
