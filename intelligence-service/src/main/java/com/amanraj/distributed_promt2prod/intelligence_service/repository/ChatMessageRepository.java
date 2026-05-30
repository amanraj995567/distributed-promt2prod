package com.amanraj.distributed_promt2prod.intelligence_service.repository;


import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatMessage;
import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
            SELECT DISTINCT m FROM ChatMessage m
            LEFT JOIN FETCH m.events e
            WHERE m.chatSession = :chatSession
            ORDER BY m.createdAt ASC, e.sequenceOrder ASC
            """)
    List<ChatMessage> findByChatSession(ChatSession chatSession);
}


//N+1 Query problem
// chat_messages Query 1
// chat_events with chat_message id: 1
// chat_events with chat_message id: 2
// chat_events with chat_message id: 3
// chat_events with chat_message id: 4
//..
// chat_events with chat_message id: N



