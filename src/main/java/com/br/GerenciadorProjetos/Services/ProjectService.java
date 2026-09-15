package com.br.GerenciadorProjetos.Services;

import com.br.GerenciadorProjetos.Dtos.*;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Enums.MemberRole;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import com.br.GerenciadorProjetos.Exceptions.TooManyProjectsException;
import com.br.GerenciadorProjetos.Exceptions.WrongMemberQuantityException;
import com.br.GerenciadorProjetos.Exceptions.WrongRoleException;
import com.br.GerenciadorProjetos.Mappers.ProjectMapper;
import com.br.GerenciadorProjetos.Repository.MemberRepository;
import com.br.GerenciadorProjetos.Repository.ProjectRepository;
import com.br.GerenciadorProjetos.Repository.Specification.ProjectSpecification;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectMapper mapper;

    public ProjectResponseDto getProjectById(Long projectId) {
        Project entity = projectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));
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
        isValidMember(requestDto);
        verifyMemberLimit(requestDto);
        projectRepository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public ProjectResponseDto updateProject(Long projectId, ProjectRequestDto requestDto){
        Project entity = projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Projeto não encontrado, atualização cancelada."));
        ProjectStatus.validStatusChange(requestDto.projectStatus(),entity.getProjectStatus());
        mapper.updateEntityFromDto(requestDto,entity);
        connectManager(requestDto,entity);
        isValidMember(requestDto);
        verifyMemberLimit(requestDto);
        projectRepository.save(entity);
        return mapper.toResponseDto(entity);
    }

    public void deleteProject(Long projectId){
        Project entity = projectRepository.findById(projectId).orElseThrow(
                () -> new EntityNotFoundException("Projeto não encontrado, nada para deletar"));
        ProjectStatus.exclusionAllowed(entity.getProjectStatus());
        projectRepository.delete(entity);
    }

    private void connectManager(ProjectRequestDto requestDto, Project projectEntity){
         Member memberEntity = memberRepository.findById(requestDto.manager())
                 .orElseThrow(() -> new EntityNotFoundException("Gerente não encontrado."));
         if(memberEntity.getRole() != MemberRole.GERENTE)
             throw new WrongRoleException("Gerente do projeto deve estar cadastrado como GERENTE");
         projectEntity.setManager(memberEntity);
    }

    private void isValidMember(ProjectRequestDto requestDto){
        if(requestDto.members()==null || requestDto.members().isEmpty())
            return;
        requestDto.members().stream().forEach(member -> {
            if(member.getRole() != MemberRole.FUNCIONARIO)
                throw new WrongRoleException("Membro do projeto deve ter atribuição de FUNCIONARIO");

            if(projectRepository.isValidMember(member.getId()) >= 3)
                throw new TooManyProjectsException("Membro não pode estar em mais de 3 projetos.");
        });
    }

    private void verifyMemberLimit(ProjectRequestDto requestDto){
        if(requestDto.members() == null)
            return;
        if(requestDto.members().size() < 1)
            throw new WrongMemberQuantityException("É necessário ter pelo menos um membro por projeto.");
        if(requestDto.members().size() > 10)
            throw new WrongMemberQuantityException("O limite de membros por projeto é 10");
    }

    public ProjectsReportDto getReport() {
        List<Project> projectList = projectRepository.findAll();

        List<ReportByStatusDto> reportByStatus = projectList.stream()
                .collect(Collectors.groupingBy(Project::getProjectStatus))
                .entrySet().stream()
                .map(entry -> new ReportByStatusDto(
                        entry.getKey().name(),
                        (long) entry.getValue().size(),
                        entry.getValue().stream()
                                .map(Project::getTotalBudget)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .toList();

        Long mediumDurationInDays = (long) projectList.stream()
                .filter(p -> p.getProjectStatus() == ProjectStatus.ENCERRADO)
                .filter(p -> p.getStartDate() != null && p.getActualEndDate() != null)
                .mapToLong(p -> ChronoUnit.DAYS.between(p.getStartDate(), p.getActualEndDate()))
                .average()
                .orElse(0);

        Long totalAllocatedMembers = projectList.stream()
                .flatMap(p -> p.getMembers().stream())
                .map(Member::getId)
                .distinct()
                .count();

        return new ProjectsReportDto(
                reportByStatus,
                mediumDurationInDays,
                totalAllocatedMembers
        );

    }
}
