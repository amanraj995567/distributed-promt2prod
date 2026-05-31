package com.amanraj.distributed_promt2prod.workspace_service.consumer;


import com.amanraj.distributed_promt2prod.common_lib.event.FileStoreRequestEvent;
import com.amanraj.distributed_promt2prod.common_lib.event.FileStoreResponseEvent;
import com.amanraj.distributed_promt2prod.workspace_service.entities.ProcessedEvent;
import com.amanraj.distributed_promt2prod.workspace_service.repository.ProcessedEventRepository;
import com.amanraj.distributed_promt2prod.workspace_service.service.ProjectFileService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class FileStorageConsumer {
    private final ProjectFileService projectFileService;
    private final ProcessedEventRepository processedEventRepository;
    private  final KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = "file-storage-event", groupId = "workspace-service")
    public void consumeFileEvent(FileStoreRequestEvent requestEvent){


        // We check Idempotency here so that  event is processed only once in the system
         boolean isPresent = processedEventRepository.existsById(requestEvent.sagaId());

         if(isPresent){
             log.info("Duplicate Saga Detected:" , requestEvent.sagaId());
             sendResponse(requestEvent, true, null);
         }

         try {
             projectFileService.saveFile(requestEvent.projectId(), requestEvent.filePath(), requestEvent.content());
             processedEventRepository.save(new ProcessedEvent(
                     requestEvent.sagaId(), LocalDateTime.now()
             ));

             sendResponse(requestEvent, true, null);
         } catch (Exception e) {
             log.error("Error in saving file", e.getMessage());
             sendResponse(requestEvent, false, e.getMessage());
         }

    }

    public void sendResponse(FileStoreRequestEvent req, boolean success, String error){
        FileStoreResponseEvent responseEvent = FileStoreResponseEvent.builder()
                .sagaId(req.sagaId())
                .projectId(req.projectId())
                .success(success)
                .errorMessage(error)
                .build();

        kafkaTemplate.send("file-storage-response", responseEvent);
    }
}
