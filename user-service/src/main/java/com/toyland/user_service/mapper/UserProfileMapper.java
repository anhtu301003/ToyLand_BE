package com.toyland.user_service.mapper;

import com.toyland.user_service.dto.request.ProfileCreationRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
