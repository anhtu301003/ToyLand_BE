package com.toyland.user_service.service;

import com.toyland.user_service.dto.request.ProfileCreationRequest;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import com.toyland.user_service.mapper.UserProfileMapper;
import com.toyland.user_service.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public UserProfileResponse getProfile(String userId){
        UserProfile userProfile = userProfileRepository.findById(userId).orElseThrow(() -> new RuntimeException("Profile not found"));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfiles(){
        var profiles = userProfileRepository.findAll();
        return profiles.stream().map(
                userProfileMapper::toUserProfileResponse).toList();
    }
}
