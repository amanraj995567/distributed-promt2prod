package com.amanraj.distributed_promt2prod.intelligence_service.consumer;


import com.amanraj.distributed_promt2prod.common_lib.enums.ChatEventStatus;
import com.amanraj.distributed_promt2prod.common_lib.event.FileStoreResponseEvent;
import com.amanraj.distributed_promt2prod.intelligence_service.repository.ChatEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class IntelligenceSagaResponseHandler {
    private  final ChatEventRepository chatEventRepository;

    @KafkaListener(topics = "file-store-response", groupId = "intelligence-group")
    @Transactional
    public void handleSagaResponse(FileStoreResponseEvent response){
        chatEventRepository.findBySagaId(response.sagaId()).ifPresent(event-> {
            // we can check idempotency with this way also
            if(!ChatEventStatus.PENDING.equals(event.getStatus())){
                log.info("Response for saga already handled. Skipping." , response.sagaId());
                return;
            }

            if(response.success()){
                event.setStatus(ChatEventStatus.CONFIRMED);
            }else{
                event.setStatus(ChatEventStatus.FAILED);
            }

        });
    }
}
