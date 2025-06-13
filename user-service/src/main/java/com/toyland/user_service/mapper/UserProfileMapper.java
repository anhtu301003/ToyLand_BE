package com.toyland.user_service.mapper;

import com.toyland.event.dto.CreateUserEvent;
import com.toyland.user_service.dto.request.CreateUserRequest;
import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserEmailProfileResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,uses = {UserAddressMapper.class})
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile entity);
    UserEmailProfileResponse toUserEmailProfileResponse(UserProfile entity);
    UserProfile toUserProfile(CreateUserRequest request);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    void updateUserProfile(@MappingTarget UserProfile userProfile,UserProfileRequest request);

    UserProfile toUserProfile(CreateUserEvent createUserEvent);
}
