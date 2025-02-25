package com.toyland.authentication_service.mapper;

import com.toyland.authentication_service.dto.request.ProfileCreationRequest;
import com.toyland.authentication_service.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
