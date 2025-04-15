package com.toyland.user_service.mapper;

import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(UserProfileRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
    void updateUserProfile(@MappingTarget UserProfile userProfile,UserProfileRequest request);
}
