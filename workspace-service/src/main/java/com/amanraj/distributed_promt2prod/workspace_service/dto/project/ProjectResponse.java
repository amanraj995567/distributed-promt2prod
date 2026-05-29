package com.amanraj.distributed_promt2prod.workspace_service.dto.project;


import java.time.Instant;

public record ProjectResponse(
        Long id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
