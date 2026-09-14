package com.br.GerenciadorProjetos.Services;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectFilterDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import com.br.GerenciadorProjetos.Mappers.ProjectMapper;
import com.br.GerenciadorProjetos.Repository.MemberRepository;
import com.br.GerenciadorProjetos.Repository.ProjectRepository;
import com.br.GerenciadorProjetos.Repository.Specification.ProjectSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    //TODO VALIDAR REGRAS DE NEGOCIO

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectMapper mapper;

    public ProjectResponseDto getProjectById(Long projectId) {
        Project entity = projectRepository.findById(projectId).get();
        return mapper.toResponseDto(entity);
    }

    public Page<ProjectResponseDto> getProjectByFilter(ProjectFilterDto filter, Pageable pageable){
        return projectRepository.findAll(ProjectSpecification.entityFilters(filter),pageable)
                .map(mapper::toResponseDto);
    }

    public ProjectResponseDto createProject(ProjectRequestDto requestDto){
        Project entity = mapper.toProjectEntity(requestDto);
        connectManager(requestDto,entity);
        entity.setProjectStatus(ProjectStatus.getByOrder(1));
        projectRepository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto){
        Project entity = projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Projeto não encontrado, atualização cancelada."));
        ProjectStatus.validStatusChange(requestDto,entity);
        mapper.updateEntityFromDto(requestDto,entity);
        connectManager(requestDto,entity);

        projectRepository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public void deleteProject(Long projectId){
        Project entity = projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Projeto não encontrado, nada para deletar"));
        projectRepository.delete(entity);
    }

    private void connectManager(ProjectRequestDto requestDto, Project projectEntity){
         Member memberEntity = memberRepository.findById(requestDto.manager())
                 .orElseThrow(() -> new EntityNotFoundException("Gerente não encontrado."));
         projectEntity.setManager(memberEntity);
    }

}
