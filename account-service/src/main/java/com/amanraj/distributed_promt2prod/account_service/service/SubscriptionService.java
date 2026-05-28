package com.amanraj.distributed_promt2prod.account_service.service;



import com.amanraj.distributed_promt2prod.account_service.dto.subscription.SubscriptionResponse;
import com.amanraj.distributed_promt2prod.common_lib.enums.SubscriptionStatus;

import java.time.Instant;

public interface SubscriptionService {

    SubscriptionResponse getCurrentSubscription();

    void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId);

    void updateSubscription(String gatewaySubscriptionId, SubscriptionStatus status, Instant perionStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId);

    void cancelSubscription(String gatewaySubscriptionId);

    void renewSubscriptionPeriod(String subId, Instant periodStart, Instant periodEnd);

    void markSubscriptionPastDue(String subId);
}
