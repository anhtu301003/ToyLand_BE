package com.toyland.user_service.mapper;

import com.toyland.user_service.dto.response.UserAddressResponse;
import com.toyland.user_service.entity.UserAddress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAddressMapper {
    UserAddress toUserAddress(UserAddress userAddress);

    UserAddressResponse toUserAddressResponse(UserAddress userAddress);
}
