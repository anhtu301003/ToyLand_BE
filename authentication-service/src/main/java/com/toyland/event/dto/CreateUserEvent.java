package com.toyland.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserEvent {
    String userId;
    String fullName;
    String gender;
    String email;
    Boolean admin;
}
