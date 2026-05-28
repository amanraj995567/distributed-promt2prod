package com.amanraj.distributed_promt2prod.account_service.dto.auth;

public record AuthResponse(
        String token,
        UserProfileResponse userProfileResponse)
{

}
