package com.toyland.user_service.service;

import com.toyland.user_service.dto.request.UserAddressRequest;
import com.toyland.user_service.dto.response.UserAddressResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.entity.UserAddress;
import com.toyland.user_service.entity.UserProfile;
import com.toyland.user_service.mapper.UserAddressMapper;
import com.toyland.user_service.mapper.UserProfileMapper;
import com.toyland.user_service.repository.UserAddressRepository;
import com.toyland.user_service.repository.UserProfileRepository;
import com.toyland.user_service.service.IService.IUserAddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserAddressService implements IUserAddressService {
    UserAddressRepository userAddressRepository;
    UserProfileRepository userProfileRepository;
    UserAddressMapper userAddressMapper;
    UserProfileMapper userProfileMapper;
    @Override
    public List<UserAddressResponse> getAddressByUserId(String userId) {
        List<UserAddress> addresses = userAddressRepository.findAllByUserUserId(userId);
        return addresses.stream().map(userAddressMapper::toUserAddressResponse).toList();
    }

    @Override
    public UserProfileResponse addAddressByUserId(String userId, UserAddressRequest userAddressRequest) {
        UserAddress userAddress = userAddressMapper.toUserAddress(userAddressRequest);
        Optional<UserProfile> optUserProfile = userProfileRepository.findByUserId(userId);
        if (optUserProfile.isPresent()) {
            UserProfile userProfile = optUserProfile.get();
            List<UserAddress> userAddresses = userProfile.getAddresses();

            if(userAddresses.isEmpty()) {
                userAddress.setIsDefault(true);
            }

            userAddress.setUser(userProfile);

            userAddresses.add(userAddress);

            userProfile.setAddresses(userAddresses);

            userAddressRepository.save(userAddress);

            return userProfileMapper.toUserProfileResponse(userProfile);
        }
        return null;
    }

    @Override
    public UserAddressResponse updateAddressByAddressId(String addressId, UserAddressRequest userAddressRequest) {
        UserAddress updatedUserAddress = userAddressRepository.findById(addressId).orElse(null);
        userAddressMapper.updateEntity(updatedUserAddress,userAddressRequest);
        return userAddressMapper.toUserAddressResponse(userAddressRepository.save(updatedUserAddress));
    }

    @Override
    public String deleteAddressByAddressId(String addressId) {
        if(userAddressRepository.existsById(addressId)) {
            userAddressRepository.deleteById(addressId);
            return "Address successfully deleted";
        }
        return "Address Deleted Failed";
    }

    @Override
    public UserProfileResponse updateAddressByDefault(String addressId) {
        return null;
    }
}
