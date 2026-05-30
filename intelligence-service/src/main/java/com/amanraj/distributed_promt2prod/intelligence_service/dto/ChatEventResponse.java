package com.amanraj.distributed_promt2prod.intelligence_service.dto;


import com.codingshuttle.distributed_lovable.common_lib.enums.ChatEventType;

public record ChatEventResponse(
        Long id,
        ChatEventType type,
        Integer sequenceOrder,
        String content,
        String filePath,
        String metadata
) {
}
