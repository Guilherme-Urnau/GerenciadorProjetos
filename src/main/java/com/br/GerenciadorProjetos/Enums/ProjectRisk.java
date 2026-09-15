package com.br.GerenciadorProjetos.Enums;

import com.br.GerenciadorProjetos.utils.BigDecimalUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public enum ProjectRisk {

    BAIXO_RISCO("Baixo risco"),
    MEDIO_RISCO("Médio risco"),
    ALTO_RISCO("Alto Risco");

    private final String name;
    private static final BigDecimal ORCAMENTO_ALTO = BigDecimal.valueOf(500000);
    private static final BigDecimal ORCAMENTO_MEDIO = BigDecimal.valueOf(100001);
    private static final Integer MESES_ALTO = 6;
    private static final Integer MESES_MEDIO = 3;

    ProjectRisk(String name) {
        this.name = name;
    }

    public static ProjectRisk calculateProjectRisk(BigDecimal totalBudget, LocalDate startDate, LocalDate estimatedEndDate) {
        Integer totalMonths = Period.between(startDate, estimatedEndDate).getMonths();
        if(BigDecimalUtils.isGreaterThanOrEqualTo(totalBudget, ORCAMENTO_ALTO))
            return ALTO_RISCO;
        if(totalMonths > MESES_ALTO)
            return ALTO_RISCO;
        if(BigDecimalUtils.isBetween(ORCAMENTO_ALTO, ORCAMENTO_MEDIO, totalBudget))
            return MEDIO_RISCO;
        if(totalMonths >= MESES_MEDIO && totalMonths <= MESES_ALTO)
            return MEDIO_RISCO;
        return BAIXO_RISCO;
    }
}
