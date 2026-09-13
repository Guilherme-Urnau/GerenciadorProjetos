package com.br.GerenciadorProjetos.Entity;

import com.br.GerenciadorProjetos.Enums.MemberRole;
import jakarta.persistence.*;

@Entity
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

}
