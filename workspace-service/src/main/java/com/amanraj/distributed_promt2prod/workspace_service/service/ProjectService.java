package com.amanraj.distributed_promt2prod.workspace_service.service;



import com.amanraj.distributed_promt2prod.common_lib.enums.ProjectPermission;
import com.amanraj.distributed_promt2prod.workspace_service.dto.project.ProjectRequest;
import com.amanraj.distributed_promt2prod.workspace_service.dto.project.ProjectResponse;
import com.amanraj.distributed_promt2prod.workspace_service.dto.project.ProjectSummaryResponse;

import java.util.List;

public interface ProjectService {
    List<ProjectSummaryResponse> getUserProjects();

    ProjectSummaryResponse getUserProjectById(Long id);

    ProjectResponse createProject(ProjectRequest request);

    ProjectResponse updateProject(Long id, ProjectRequest request);

    void softDelete(Long id);

    boolean hasPermission(Long projectId, ProjectPermission permission);
}
