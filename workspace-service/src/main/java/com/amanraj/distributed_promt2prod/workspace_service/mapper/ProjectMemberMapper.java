package com.amanraj.distributed_promt2prod.workspace_service.mapper;

import com.amanraj.distributed_promt2prod.workspace_service.dto.member.MemberResponse;
import com.amanraj.distributed_promt2prod.workspace_service.entities.ProjectMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id.userId")
    MemberResponse toProjectMemberResponseFromMember(ProjectMember projectMember);
}
