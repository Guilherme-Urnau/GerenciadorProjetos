package com.br.GerenciadorProjetos.Entity;

import com.br.GerenciadorProjetos.Enums.MemberRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "members")
    private List<Project> projects = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole role;

}
