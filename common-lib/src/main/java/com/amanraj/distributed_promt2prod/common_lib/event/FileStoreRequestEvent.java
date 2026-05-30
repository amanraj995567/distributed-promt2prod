package com.amanraj.distributed_promt2prod.common_lib.event;

public record FileStoreRequestEvent(
        Long projectId,
        String filePath,
        String content,
        Long userId
) {


}
