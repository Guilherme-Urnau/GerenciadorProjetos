package com.br.GerenciadorProjetos.Dtos;

import com.br.GerenciadorProjetos.Entity.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProjectRequestDto(

        @NotBlank
        String name,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate startDate,

        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate estimatedEndDate,

        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate actualEndDate,

        @NotNull
        BigDecimal totalBudget,

        @NotBlank
        String description,

        @NotNull
        Long manager,

        List<Member> members
) {}
