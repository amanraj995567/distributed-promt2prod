package com.amanraj.distributed_promt2prod.account_service.service.impl;

import com.amanraj.distributed_promt2prod.account_service.dto.subscription.SubscriptionResponse;
import com.amanraj.distributed_promt2prod.account_service.entity.Plan;
import com.amanraj.distributed_promt2prod.account_service.entity.Subscription;
import com.amanraj.distributed_promt2prod.account_service.entity.User;
import com.amanraj.distributed_promt2prod.account_service.mapper.SubscriptionMapper;
import com.amanraj.distributed_promt2prod.account_service.repository.PlanRepository;
import com.amanraj.distributed_promt2prod.account_service.repository.SubscriptionRespository;
import com.amanraj.distributed_promt2prod.account_service.repository.UserRepository;
import com.amanraj.distributed_promt2prod.account_service.service.SubscriptionService;
import com.amanraj.distributed_promt2prod.common_lib.enums.SubscriptionStatus;
import com.amanraj.distributed_promt2prod.common_lib.error.ResourceNotFoundException;
import com.amanraj.distributed_promt2prod.common_lib.security.AuthUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final AuthUtil authUtil;
    private final SubscriptionRespository subscriptionRespository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final PlanRepository planRepository;

    @Override
    public SubscriptionResponse getCurrentSubscription() {
        Long userId = authUtil.getCurrentUserId();
        var currentSubscription = subscriptionRespository.findByUserIdAndStatusIn(userId, Set.of(
                SubscriptionStatus.ACTIVE, SubscriptionStatus.PAST_DUE,
                SubscriptionStatus.TRAILING
        )).orElse(
                new Subscription()
        );

        return subscriptionMapper.toSubscriptionResponse(currentSubscription);
    }

    @Override
    public void activateSubscription(Long userId, Long planId, String subscriptionId, String customerId) {
        // crete a new subscription object
        boolean exists = subscriptionRespository.existsByStripeSubscriptionId(subscriptionId);

        if (exists) return;

        User user = getUser(userId);
        Plan plan = getPlan(planId);

        Subscription subscription = Subscription.builder().
                user(user)
                .plan(plan)
                .stripeSubscriptionId(subscriptionId)
                .status(SubscriptionStatus.INCOMPLETE)
                .build();

        subscriptionRespository.save(subscription);

    }

    @Override
    @Transactional
    public void updateSubscription(String gatewaySubscriptionId, SubscriptionStatus status,
                                   Instant perionStart, Instant periodEnd, Boolean cancelAtPeriodEnd, Long planId) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);
        boolean hasSubscriptionUpdated = false;

        if (status != null && subscription.getStatus() != status) {
            subscription.setStatus(status);
            hasSubscriptionUpdated = true;
        }

        if (perionStart != null && !perionStart.equals(subscription.getCurentPeriodStart())) {
            subscription.setCurentPeriodStart(perionStart);
            hasSubscriptionUpdated = true;
        }

        if (periodEnd != null && !periodEnd.equals(subscription.getCurrentPeriodEnd())) {
            subscription.setCurrentPeriodEnd(periodEnd);
            hasSubscriptionUpdated = true;
        }

        if (cancelAtPeriodEnd != null && cancelAtPeriodEnd != subscription.getCancelAtPeriodEnd()) {
            subscription.setCancelAtPeriodEnd(cancelAtPeriodEnd);
            hasSubscriptionUpdated = true;
        }

        if (planId != null && planId != subscription.getPlan().getId()) {
            Plan newPlan = getPlan(planId);
            subscription.setPlan(newPlan);
            hasSubscriptionUpdated = true;
        }

        if (hasSubscriptionUpdated) {
            log.debug("Subscription has been updated: {}", gatewaySubscriptionId);
            subscriptionRespository.save(subscription);
        }

    }

    @Override
    public void cancelSubscription(String gatewaySubscriptionId) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);
        subscription.setStatus(SubscriptionStatus.CANCELED);
        subscriptionRespository.save(subscription);
    }

    @Override
    public void renewSubscriptionPeriod(String gatewaySubscriptionId, Instant periodStart, Instant periodEnd) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);

        Instant newStart = periodStart != null ? periodStart : subscription.getCurrentPeriodEnd();
        subscription.setCurentPeriodStart(newStart);
        subscription.setCurrentPeriodEnd(periodEnd);

        if (subscription.getStatus() == SubscriptionStatus.PAST_DUE || subscription.getStatus() == SubscriptionStatus.INCOMPLETE) {
            subscription.setStatus(SubscriptionStatus.ACTIVE);
        }

        subscriptionRespository.save(subscription);
    }

    @Override
    public void markSubscriptionPastDue(String gatewaySubscriptionId) {
        Subscription subscription = getSubscription(gatewaySubscriptionId);
        if (subscription.getStatus() == SubscriptionStatus.PAST_DUE) {
            log.debug("Subscription is already past due, gatewaySubscriptionId: {}", gatewaySubscriptionId);
        }
        subscription.setStatus(SubscriptionStatus.PAST_DUE);
        subscriptionRespository.save(subscription);

        // Notify user via email


    }


    // UtilityMethod

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
    }

    private Plan getPlan(Long planId) {
        return planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException("Plan", planId.toString()));
    }

    private Subscription getSubscription(String gatewaySubscriptionId) {
        return subscriptionRespository.findByStripeSubscriptionId(gatewaySubscriptionId).orElseThrow(
                () -> new ResourceNotFoundException("Subscription not found", gatewaySubscriptionId.toString()));
    }

}