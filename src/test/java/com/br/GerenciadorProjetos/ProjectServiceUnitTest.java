package com.br.GerenciadorProjetos;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Enums.MemberRole;
import com.br.GerenciadorProjetos.Enums.ProjectRisk;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import com.br.GerenciadorProjetos.Exceptions.ProjectExclusionNotAllowedException;
import com.br.GerenciadorProjetos.Exceptions.WrongMemberQuantityException;
import com.br.GerenciadorProjetos.Exceptions.WrongRoleException;
import com.br.GerenciadorProjetos.Mappers.ProjectMapper;
import com.br.GerenciadorProjetos.Repository.MemberRepository;
import com.br.GerenciadorProjetos.Repository.ProjectRepository;
import com.br.GerenciadorProjetos.Services.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceUnitTest {

    @Mock private ProjectRepository projectRepository;
    @Mock private MemberRepository memberRepository;
    @Mock private ProjectMapper projectMapper;

    @InjectMocks
    private ProjectService service;

    @Test
    public void erroQuantiaMembros_criacaoProjeto(){

        Member gerente = new Member();
        gerente.setId(1L);
        gerente.setRole(MemberRole.GERENTE);

        Project project = new Project();
        ProjectRequestDto requestDto = mock(ProjectRequestDto.class);
        when(requestDto.manager()).thenReturn(1L);
        when(requestDto.members()).thenReturn(List.of());
        when(projectMapper.toProjectEntity(requestDto)).thenReturn(project);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(gerente));

        assertThrows(WrongMemberQuantityException.class, () -> service.createProject(requestDto));
        verify(projectRepository, never()).save(any());
    }

    @Test
    public void erroMembrosDemais_criacaoProjeto(){

        Member gerente = new Member();
        gerente.setId(1L);
        gerente.setRole(MemberRole.GERENTE);

        Member funcionario = new Member();
        funcionario.setId(2L);
        funcionario.setRole(MemberRole.FUNCIONARIO);

        Project project = new Project();
        ProjectRequestDto requestDto = mock(ProjectRequestDto.class);
        when(requestDto.manager()).thenReturn(1L);
        when(requestDto.members()).thenReturn(List.of());
        when(projectMapper.toProjectEntity(requestDto)).thenReturn(project);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(requestDto.members()).thenReturn(List.of(funcionario, funcionario, funcionario, funcionario
            , funcionario, funcionario, funcionario, funcionario, funcionario, funcionario, funcionario));

        assertThrows(WrongMemberQuantityException.class, () -> service.createProject(requestDto));
        verify(projectRepository, never()).save(any());
    }

    @Test
    public void erroGerenteAtribuicaoErrada_criacaoProjeto(){

        Member gerente = new Member();
        gerente.setId(1L);
        gerente.setRole(MemberRole.FUNCIONARIO);

        Project project = new Project();
        ProjectRequestDto requestDto = mock(ProjectRequestDto.class);
        when(requestDto.manager()).thenReturn(1L);
        when(projectMapper.toProjectEntity(requestDto)).thenReturn(project);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(gerente));

        assertThrows(WrongRoleException.class, () -> service.createProject(requestDto));
        verify(projectRepository, never()).save(any());
    }

    @Test
    public void erroFuncionarioAtribuicaoErrada_criacaoProjeto(){

        Member gerente = new Member();
        gerente.setId(1L);
        gerente.setRole(MemberRole.GERENTE);

        Member funcionario = new Member();
        funcionario.setId(2L);
        funcionario.setRole(MemberRole.GERENTE);

        Project project = new Project();
        ProjectRequestDto requestDto = mock(ProjectRequestDto.class);
        when(requestDto.manager()).thenReturn(1L);
        when(projectMapper.toProjectEntity(requestDto)).thenReturn(project);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(gerente));
        when(requestDto.members()).thenReturn(List.of(funcionario));

        assertThrows(WrongRoleException.class, () -> service.createProject(requestDto));
        verify(projectRepository, never()).save(any());

    }

    @Test
    public void projetoComRiscoBaixo_Valor10MileUmMes(){

        Project project = new Project();
        project.setTotalBudget(BigDecimal.valueOf(10000));
        project.setStartDate(LocalDate.of(2026,1,1));
        project.setEstimatedEndDate(LocalDate.of(2026,2,1));

        assertEquals(ProjectRisk.BAIXO_RISCO, project.getProjectRisk());

    }

    @Test
    public void projetoComRiscoBaixo_valor99mileDoisMeses(){

        Project project = new Project();
        project.setTotalBudget(BigDecimal.valueOf(10000));
        project.setStartDate(LocalDate.of(2026,1,1));
        project.setEstimatedEndDate(LocalDate.of(2026,3,2));

        assertEquals(ProjectRisk.BAIXO_RISCO, project.getProjectRisk());

    }


    @Test
    public void projetoComRiscoMedio_200MileQuatroMeses(){

        Project project = new Project();
        project.setTotalBudget(BigDecimal.valueOf(200000));
        project.setStartDate(LocalDate.of(2026,1,1));
        project.setEstimatedEndDate(LocalDate.of(2026,5,2));

        assertEquals(ProjectRisk.MEDIO_RISCO, project.getProjectRisk());

    }

    @Test
    public void projetoComRiscoAlto_600MileQuatroMeses(){

        Project project = new Project();
        project.setTotalBudget(BigDecimal.valueOf(600000));
        project.setStartDate(LocalDate.of(2026,1,1));
        project.setEstimatedEndDate(LocalDate.of(2026,5,2));

        assertEquals(ProjectRisk.ALTO_RISCO, project.getProjectRisk());

    }

    @Test
    public void projetoComRiscoAlto_400MileSeteMeses(){

        Project project = new Project();
        project.setTotalBudget(BigDecimal.valueOf(400000));
        project.setStartDate(LocalDate.of(2026,1,1));
        project.setEstimatedEndDate(LocalDate.of(2026,8,2));

        assertEquals(ProjectRisk.ALTO_RISCO, project.getProjectRisk());

    }

    @Test
    public void projetoComRiscoAlto_600MileSeteMeses(){

        Project project = new Project();
        project.setTotalBudget(BigDecimal.valueOf(600000));
        project.setStartDate(LocalDate.of(2026,1,1));
        project.setEstimatedEndDate(LocalDate.of(2026,8,2));

        assertEquals(ProjectRisk.ALTO_RISCO, project.getProjectRisk());

    }

    @Test
    public void erroStatusImpedeExclusao_exclusaoProjeto(){

        Project project = new Project();
        project.setProjectStatus(ProjectStatus.INICIADO);
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        assertThrows(ProjectExclusionNotAllowedException.class, () -> service.deleteProject(1L));
        verify(projectRepository, never()).delete(project);

    }










}