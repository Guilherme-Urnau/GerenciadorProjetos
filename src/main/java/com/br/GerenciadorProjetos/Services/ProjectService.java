package com.br.GerenciadorProjetos.Services;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectFilterDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Mappers.ProjectMapper;
import com.br.GerenciadorProjetos.Repository.ProjectRepository;
import com.br.GerenciadorProjetos.Repository.Specification.ProjectSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    //TODO VALIDAR REGRAS DE NEGOCIO

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    public ProjectResponseDto getProjectById(Long projectId) {
        Project entity = repository.findById(projectId).get();
        return mapper.toResponseDto(entity);
    }

    public Page<ProjectResponseDto> getProjectByFilter(ProjectFilterDto filter, Pageable pageable){
        return repository.findAll(ProjectSpecification.entityFilters(filter),pageable)
                .map(mapper::toResponseDto);
    }

    public ProjectResponseDto createProject(ProjectRequestDto requestDto){
        Project entity = mapper.toProjectEntity(requestDto);
        repository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto){
        Project entity = repository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Projeto não encontrado, atualização cancelada."));
        mapper.updateEntityFromDto(requestDto,entity);

        repository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public void deleteProject(Long projectId){
        Project entity = repository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Projeto não encontrado, nada para deletar"));
        repository.delete(entity);
    }



}
