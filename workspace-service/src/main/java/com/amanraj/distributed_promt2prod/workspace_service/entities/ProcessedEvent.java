package com.amanraj.distributed_promt2prod.workspace_service.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "processed_events")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProcessedEvent {
    @Id
    private String sagaId;
    private LocalDateTime processedAt;
}
