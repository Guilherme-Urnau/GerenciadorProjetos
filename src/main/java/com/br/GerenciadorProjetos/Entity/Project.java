package com.br.GerenciadorProjetos.Entity;

import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "manager_id",nullable = false)
    private ProjectMember manager;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus projectStatus;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> members = new ArrayList<>();

}
