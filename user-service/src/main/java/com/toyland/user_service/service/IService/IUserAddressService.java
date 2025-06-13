package com.toyland.user_service.service.IService;

import com.toyland.user_service.dto.request.UserAddressRequest;
import com.toyland.user_service.dto.response.UserAddressResponse;
import com.toyland.user_service.dto.response.UserProfileResponse;
import com.toyland.user_service.service.UserProfileService;

import java.util.List;

public interface IUserAddressService {
    List<UserAddressResponse> getAddressByUserId(String userId);

    UserProfileResponse addAddressByUserId(String userId, UserAddressRequest userAddressRequest);

    UserAddressResponse updateAddressByAddressId(String addressId, UserAddressRequest userAddressRequest);

    String deleteAddressByAddressId(String addressId);

    UserProfileResponse updateAddressByDefault(String addressId);
}
