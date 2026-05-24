package com.amanraj.distributed_promt2prod.common_lib.error;

import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiError(
        HttpStatus status,
        String message,
        Instant timestamp,
        List<ApiFieldError> errors

) {
    public ApiError(HttpStatus status, String message) {
        this(status, message, Instant.now(), null);
    }

    public ApiError(HttpStatus status, String message, List<ApiFieldError> erros) {
        this(status, message, Instant.now(), erros);
    }
}

