package com.br.GerenciadorProjetos.Dtos;

import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Enums.ProjectRisk;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProjectResponseDto(

        Long id,

        String name,

        LocalDate startDate,

        LocalDate estimatedEndDate,

        LocalDate actualEndDate,

        BigDecimal totalBudget,

        String description,

        Member manager,

        ProjectStatus projectStatus,

        List<Member> members,

        ProjectRisk projectRisk

) {}
