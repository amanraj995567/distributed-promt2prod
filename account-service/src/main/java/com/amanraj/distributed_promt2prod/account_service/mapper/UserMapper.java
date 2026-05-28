package com.amanraj.distributed_promt2prod.account_service.mapper;


import com.amanraj.distributed_promt2prod.account_service.dto.auth.SignupRequest;
import com.amanraj.distributed_promt2prod.account_service.dto.auth.UserProfileResponse;
import com.amanraj.distributed_promt2prod.account_service.entity.User;
import com.amanraj.distributed_promt2prod.common_lib.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(SignupRequest signupRequest);

    UserProfileResponse toUserProfileResponse(User user);

    UserDto  toUserDto(User user);
}
