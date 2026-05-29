package com.amanraj.distributed_promt2prod.workspace_service.dto.member;


import com.codingshuttle.distributed_lovable.common_lib.enums.ProjectRole;

import java.time.Instant;

public record MemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole projectRole,
        Instant invitedAt
) {
}
