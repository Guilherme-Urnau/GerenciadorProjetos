package com.br.GerenciadorProjetos.Dtos;

import com.br.GerenciadorProjetos.Enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProjectFilterDto(

        Long projectId,

        String name,

        LocalDate startDate,

        LocalDate estimatedEndDate,

        LocalDate actualEndDate,

        BigDecimal totalBudget,

        String description,

        Long manager,

        ProjectStatus projectStatus
) {}
