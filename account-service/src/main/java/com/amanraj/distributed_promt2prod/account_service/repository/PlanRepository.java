package com.amanraj.distributed_promt2prod.account_service.repository;

import com.amanraj.distributed_promt2prod.account_service.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

    Optional<Plan> findByStripePriceId(String id);
}
