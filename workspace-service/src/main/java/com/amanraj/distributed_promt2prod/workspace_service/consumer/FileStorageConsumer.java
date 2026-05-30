package com.amanraj.distributed_promt2prod.workspace_service.consumer;


import com.amanraj.distributed_promt2prod.common_lib.event.FileStoreRequestEvent;
import com.amanraj.distributed_promt2prod.workspace_service.service.ProjectFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class FileStorageConsumer {
    private final ProjectFileService projectFileService;
    @KafkaListener(topics = "file-storage-event", groupId = "workspace-service")
    public void consumeFileEvent(FileStoreRequestEvent requestEvent){
          projectFileService.saveFile(requestEvent.projectId(), requestEvent.filePath(), requestEvent.content());
    }
}
