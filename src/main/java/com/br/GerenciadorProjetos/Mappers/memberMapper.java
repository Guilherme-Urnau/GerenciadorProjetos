package com.br.GerenciadorProjetos.Mappers;

import com.br.GerenciadorProjetos.Dtos.MemberRequestDto;
import com.br.GerenciadorProjetos.Dtos.MemberResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface memberMapper {

    @Mapping(target = "projects", ignore = true)
    Member toMemberEntity(MemberRequestDto dto);

    MemberResponseDto toMemberResponseDto(Member member);

    default Long mapProjectId(Project projectEntity){
        if (projectEntity == null) return null;
        return projectEntity.getId();
    }
}
