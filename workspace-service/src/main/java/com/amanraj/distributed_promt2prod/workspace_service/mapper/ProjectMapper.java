package com.amanraj.distributed_promt2prod.workspace_service.mapper;


import com.amanraj.distributed_promt2prod.common_lib.enums.ProjectRole;
import com.amanraj.distributed_promt2prod.workspace_service.dto.project.ProjectResponse;
import com.amanraj.distributed_promt2prod.workspace_service.dto.project.ProjectSummaryResponse;
import com.amanraj.distributed_promt2prod.workspace_service.entities.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project, ProjectRole role);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}
