package com.toyland.user_service.service;

import com.toyland.user_service.dto.request.ProfileCreationRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import com.toyland.user_service.mapper.UserProfileMapper;
import com.toyland.user_service.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserProfileService {
    UserProfileRepository userProfileRepository;

    UserProfileMapper userProfileMapper;

    public UserProfileResponse createUserProfile(ProfileCreationRequest request){
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

}
