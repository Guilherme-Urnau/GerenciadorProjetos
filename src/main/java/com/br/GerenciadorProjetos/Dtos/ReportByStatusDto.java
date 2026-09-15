package com.br.GerenciadorProjetos.Dtos;

import java.math.BigDecimal;

public record ReportByStatusDto(

        String projectStatus,

        Long totalProjects,

        BigDecimal totalBudget

) {}
