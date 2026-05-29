package com.amanraj.distributed_promt2prod.workspace_service.mapper;


import com.amanraj.distributed_promt2prod.workspace_service.entities.ProjectFile;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectFileMapper {

    List<FileNode> toListOfFileNode(List<ProjectFile> projectFileList);
}
