package com.toyland.event.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryQuantityProductRequest {
    List<ChangeProductRequest> products;
}
