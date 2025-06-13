package com.toyland.user_service.mapper;

import com.toyland.user_service.dto.request.UserAddressRequest;
import com.toyland.user_service.dto.response.UserAddressResponse;
import com.toyland.user_service.entity.UserAddress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserAddressMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "isDefault", ignore = true)
    UserAddress toUserAddress(UserAddressRequest userAddressRequest);

    @Mapping(target = "userId", source = "user.userId")
    UserAddressResponse toUserAddressResponse(UserAddress userAddress);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(@MappingTarget UserAddress userAddress, UserAddressRequest request);
}
