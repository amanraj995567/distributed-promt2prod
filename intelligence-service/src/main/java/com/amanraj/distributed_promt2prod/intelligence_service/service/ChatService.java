package com.amanraj.distributed_promt2prod.intelligence_service.service;

import com.amanraj.distributed_promt2prod.intelligence_service.dto.ChatResponse;

import java.util.List;

public interface ChatService {

    List<ChatResponse> getProjectChatHistory(Long projectId);
}
