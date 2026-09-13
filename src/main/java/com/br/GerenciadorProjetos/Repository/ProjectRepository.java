package com.br.GerenciadorProjetos.Repository;

import com.br.GerenciadorProjetos.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
