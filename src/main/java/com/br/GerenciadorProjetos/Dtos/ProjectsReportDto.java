package com.br.GerenciadorProjetos.Dtos;

import java.math.BigDecimal;
import java.util.List;

public record ProjectsReportDto(

        List<ReportByStatusDto> byStatus,

        Long mediumDurationInDays,

        Long totalAllocatedMembers

) {}
