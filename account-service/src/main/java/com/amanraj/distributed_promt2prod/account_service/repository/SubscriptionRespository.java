package com.amanraj.distributed_promt2prod.account_service.repository;

import com.amanraj.distributed_promt2prod.account_service.entity.Subscription;
import com.amanraj.distributed_promt2prod.common_lib.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface SubscriptionRespository extends JpaRepository<Subscription, Long> {


    // Get the current active subscription
    Optional<Subscription> findByUserIdAndStatusIn(Long userId, Set<SubscriptionStatus> statusSet);

    boolean existsByStripeSubscriptionId(String subscriptionId);

    Optional<Subscription> findByStripeSubscriptionId(String gatewaySubscriptionId);

    void save(Subscription subscription);
}
