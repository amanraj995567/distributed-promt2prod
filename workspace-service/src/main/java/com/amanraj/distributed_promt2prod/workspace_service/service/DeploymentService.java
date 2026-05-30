package com.amanraj.distributed_promt2prod.workspace_service.service;


import com.amanraj.distributed_promt2prod.workspace_service.dto.project.DeployResponse;

public interface DeploymentService {
    DeployResponse deploy(Long projectId);
}
