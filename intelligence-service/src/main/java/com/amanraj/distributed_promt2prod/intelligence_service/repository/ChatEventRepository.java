package com.amanraj.distributed_promt2prod.intelligence_service.repository;


import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatEventRepository extends JpaRepository<ChatEvent, Long> {
    Optional<ChatEvent> findBySagaId(String s);
}
