package com.toyland.authentication_service.mapper;

import com.toyland.authentication_service.dto.request.PermissionRequest;
import com.toyland.authentication_service.dto.response.PermissionResponse;
import com.toyland.authentication_service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
