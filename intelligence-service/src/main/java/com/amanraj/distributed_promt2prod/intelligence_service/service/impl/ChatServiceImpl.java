package com.amanraj.distributed_promt2prod.intelligence_service.service.impl;


import com.amanraj.distributed_promt2prod.common_lib.security.AuthUtil;
import com.amanraj.distributed_promt2prod.intelligence_service.dto.ChatResponse;
import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatMessage;
import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatSession;
import com.amanraj.distributed_promt2prod.intelligence_service.entities.ChatSessionId;
import com.amanraj.distributed_promt2prod.intelligence_service.repository.ChatMessageRepository;
import com.amanraj.distributed_promt2prod.intelligence_service.repository.ChatSessionRepository;
import com.amanraj.distributed_promt2prod.intelligence_service.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatSessionRepository chatSessionRepository;
    private final AuthUtil authUtil;
    private final ChatMapper chatMapper;

    @Override
    public List<ChatResponse> getProjectChatHistory(Long projectId) {
        Long userId = authUtil.getCurrentUserId();

        ChatSession chatSession = chatSessionRepository.getReferenceById(
                new ChatSessionId(projectId, userId)
        );

        List<ChatMessage> chatMessageList = chatMessageRepository.findByChatSession(chatSession);

        return chatMapper.fromListOfChatMessage(chatMessageList);
    }
}
