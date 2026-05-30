package com.amanraj.distributed_promt2prod.intelligence_service.repository;

import com.amanraj.distributed_promt2prod.intelligence_service.entities.UsageLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface UsageLogRepository extends JpaRepository<UsageLog, Long> {
    Optional<UsageLog> findByUserIdAndDate(Long userId, LocalDate today);
}
