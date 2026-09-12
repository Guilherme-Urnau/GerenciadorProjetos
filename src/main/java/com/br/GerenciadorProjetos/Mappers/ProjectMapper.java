package com.br.GerenciadorProjetos.Mappers;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {

    Project toProjectEntity(ProjectRequestDto requestDto);

    ProjectResponseDto toResponseDto(Project entity);

    void updateEntityFromDto(ProjectRequestDto requestDto, @MappingTarget Project entity);

}
