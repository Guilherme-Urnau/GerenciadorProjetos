package com.br.GerenciadorProjetos.Dtos;

import com.br.GerenciadorProjetos.Entity.ProjectMember;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProjectFilterDto(

        Long projectId,

        String name,

        LocalDate startDate,

        LocalDate estimatedEndDate,

        LocalDate ActualEndDate,

        BigDecimal totalBudget,

        String description,

        Long manager,

        ProjectStatus projectStatus
) {}
