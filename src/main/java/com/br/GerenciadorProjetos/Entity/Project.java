package com.br.GerenciadorProjetos.Entity;

import com.br.GerenciadorProjetos.Enums.ProjectRisk;
import com.br.GerenciadorProjetos.Enums.ProjectStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private LocalDate startDate;

    private LocalDate estimatedEndDate;

    private LocalDate actualEndDate;

    private BigDecimal totalBudget;

    private String description;

    @ManyToOne
    @JoinColumn(name = "manager_id",nullable = false)
    private Member manager;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus projectStatus;

    @ManyToMany
    @JoinTable(
            name = "project_members",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    private List<Member> members = new ArrayList<>();

    @Transient
    private ProjectRisk projectRisk;

    public ProjectRisk getProjectRisk() {
        return ProjectRisk.calculateProjectRisk(totalBudget, startDate, estimatedEndDate);
    }
}
