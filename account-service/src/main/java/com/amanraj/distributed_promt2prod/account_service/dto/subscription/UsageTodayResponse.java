package com.amanraj.distributed_promt2prod.account_service.dto.subscription;

public record UsageTodayResponse(
        Integer tokenUsed,
        Integer tokenLimit,
        Integer previewsRunning,
        Integer previewLimit
) {
}
