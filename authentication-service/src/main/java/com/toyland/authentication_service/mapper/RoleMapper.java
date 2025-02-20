package com.toyland.authentication_service.mapper;

import com.toyland.authentication_service.dto.request.RoleRequest;
import com.toyland.authentication_service.dto.response.RoleResponse;
import com.toyland.authentication_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
