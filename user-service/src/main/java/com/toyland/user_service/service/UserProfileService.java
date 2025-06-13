package com.toyland.user_service.service;

import com.toyland.event.dto.CreateUserEvent;
import com.toyland.user_service.dto.request.CreateUserRequest;
import com.toyland.user_service.dto.request.UserProfileRequest;
import com.toyland.user_service.dto.response.UserEmailProfileResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserProfile;
import com.toyland.user_service.exception.AppException;
import com.toyland.user_service.exception.ErrorCode;
import com.toyland.user_service.mapper.UserProfileMapper;
import com.toyland.user_service.repository.UserProfileRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserProfileService {
    UserProfileRepository userProfileRepository;

    UserProfileMapper userProfileMapper;

    @Transactional
    public UserProfileResponse createUserProfile(CreateUserRequest request){
        UserProfile userProfile = userProfileRepository.save(userProfileMapper.toUserProfile(request));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Transactional
    public void createUserProfile(CreateUserEvent createUserEvent){
        UserProfile userProfile = userProfileMapper.toUserProfile(createUserEvent);
        userProfileRepository.save(userProfile);
    }

    public UserProfileResponse getProfile(String userId){
        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(userId);
        if(userProfile.isPresent()){
            return userProfileMapper.toUserProfileResponse(userProfile.get());
        }else{
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }

    public UserEmailProfileResponse getProfileEmail(String userId){
        Optional<UserProfile> userProfile = userProfileRepository.findByUserId(userId);
        if(userProfile.isPresent()){
            return userProfileMapper.toUserEmailProfileResponse(userProfile.get());
        }
        else{
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserProfileResponse> getAllProfiles(Pageable Pageable){
        return userProfileRepository.findAll(Pageable).map(uprofile -> userProfileMapper.toUserProfileResponse(uprofile));
    }

    @Transactional
    public UserProfileResponse updateUserProfile(String id,UserProfileRequest request) {
        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userProfileMapper.updateUserProfile(userProfile,request);

        userProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Transactional
    public String deleteUserProfile(String id) {
        try{
            Optional<UserProfile> userProfile = userProfileRepository.findByUserId(id);
            if(userProfile.isPresent()){
                userProfileRepository.deleteByUserId(id);
                return "delete success";
            }else{
                throw new AppException(ErrorCode.USER_NOT_EXISTED);
            }
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
