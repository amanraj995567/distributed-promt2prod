package com.amanraj.distributed_promt2prod.intelligence_service.repository;


import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatSession;
import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatSessionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatSessionRepository extends JpaRepository<ChatSession, ChatSessionId> {
}
