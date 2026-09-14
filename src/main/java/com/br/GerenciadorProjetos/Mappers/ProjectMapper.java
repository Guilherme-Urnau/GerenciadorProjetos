package com.br.GerenciadorProjetos.Mappers;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    @Mapping(target = "manager", ignore = true)
    Project toProjectEntity(ProjectRequestDto requestDto);

    ProjectResponseDto toResponseDto(Project entity);

    @Mapping(target = "manager", ignore = true)
    void updateEntityFromDto(ProjectRequestDto requestDto, @MappingTarget Project entity);

}
