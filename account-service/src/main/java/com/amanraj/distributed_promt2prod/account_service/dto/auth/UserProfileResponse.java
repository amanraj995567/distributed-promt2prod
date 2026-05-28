package com.amanraj.distributed_promt2prod.account_service.dto.auth;

public record UserProfileResponse(
        Long id,
        String name,
        String username
) {

}
