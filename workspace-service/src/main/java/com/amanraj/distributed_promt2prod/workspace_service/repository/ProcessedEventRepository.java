package com.amanraj.distributed_promt2prod.workspace_service.repository;

import com.amanraj.distributed_promt2prod.workspace_service.entities.ProcessedEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedEventRepository extends JpaRepository<ProcessedEvent, String> {
}
