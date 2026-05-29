package com.amanraj.distributed_promt2prod.workspace_service.service;


import com.amanraj.distributed_promt2prod.common_lib.dto.FileTreeDto;

public interface ProjectFileService {
    FileTreeDto getFileTree(Long projectId);

    String getFileContent(Long projectId, String path);

    void saveFile(Long projectId, String filePath, String fileContent);
}
