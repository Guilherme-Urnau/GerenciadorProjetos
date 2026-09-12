package com.br.GerenciadorProjetos.Dtos;

import com.br.GerenciadorProjetos.Entity.ProjectMember;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProjectRequestDto(

        @NotNull
        Long projectId,

        @NotBlank
        String name,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate estimatedEndDate,

        LocalDate ActualEndDate,

        @NotNull
        BigDecimal totalBudget,

        @NotBlank
        String description,

        @NotNull
        Long manager,

        @NotEmpty
        List<ProjectMember> members
) {}
