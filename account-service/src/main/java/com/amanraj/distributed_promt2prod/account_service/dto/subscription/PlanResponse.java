package com.amanraj.distributed_promt2prod.account_service.dto.subscription;

public record PlanResponse(
        long id,
        String name,
        String maxProjects,
        String maxTokensPerDay,
        Boolean unlimitedAi,
        String price
) {
}
