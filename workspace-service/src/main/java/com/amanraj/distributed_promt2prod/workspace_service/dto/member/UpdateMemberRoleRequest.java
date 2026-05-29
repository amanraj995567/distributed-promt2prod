package com.amanraj.distributed_promt2prod.workspace_service.dto.member;

import com.codingshuttle.distributed_lovable.common_lib.enums.ProjectRole;
import jakarta.validation.constraints.NotNull;

public record UpdateMemberRoleRequest(
        @NotNull ProjectRole role) {
}
