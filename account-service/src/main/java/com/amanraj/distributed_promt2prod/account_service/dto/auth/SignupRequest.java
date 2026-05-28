package com.amanraj.distributed_promt2prod.account_service.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @Email @NotBlank String username,
        @Size(min = 1, max = 15) String name,
        @Size(min = 4) String password
) {
}
