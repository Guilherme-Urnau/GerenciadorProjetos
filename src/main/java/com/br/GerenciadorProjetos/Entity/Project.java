package com.br.GerenciadorProjetos.Entity;

import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    private LocalDate estimatedEndDate;

    private LocalDate ActualEndDate;

    private BigDecimal totalBudget;

    private String description;

    private Long manager;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus projectStatus;


}
