package com.br.GerenciadorProjetos.Services;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectFilterDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Mappers.ProjectMapper;
import com.br.GerenciadorProjetos.Repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final ProjectMapper mapper;

    public Page<ProjectRequestDto> getProjectByFilter(ProjectFilterDto filter, Pageable pageable){
        return null;
    }

    public ProjectResponseDto createProject(ProjectRequestDto requestDto){
        Project entity = mapper.toProjectEntity(requestDto);
        ProjectResponseDto responseDto = mapper.toResponseDto(repository.save(entity));
        return responseDto;
    }

    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto){
        Project entity = repository.findById(projectId).orElseThrow(() ->
                new EntityNotFoundException("Projeto não encontrado, atualização cancelada."));
        mapper.updateEntityFromDto(requestDto,entity);

        repository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public void deleteProject(Long projectId){
        Project entity = repository.findById(projectId).orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado, nada para deletar"));
        repository.delete(entity);
        return;
    }


}
