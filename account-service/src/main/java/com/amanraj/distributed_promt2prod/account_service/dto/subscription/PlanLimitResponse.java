package com.amanraj.distributed_promt2prod.account_service.dto.subscription;

public record PlanLimitResponse(
        String planName,
        Integer maxTokenPerDay,
        Integer maxProjects,
        boolean islimitedAi
) {
}
