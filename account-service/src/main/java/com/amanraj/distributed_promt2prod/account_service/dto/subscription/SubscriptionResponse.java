package com.amanraj.distributed_promt2prod.account_service.dto.subscription;

import com.amanraj.distributed_promt2prod.common_lib.dto.PlanDto;

import java.time.Instant;

public record SubscriptionResponse(
        PlanDto plan,
        String status,
        Instant currentPeriodEnd,
        Long tokenUsedThisCycle
) {

}
