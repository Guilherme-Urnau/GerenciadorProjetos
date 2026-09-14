package com.br.GerenciadorProjetos.Repository;

import com.br.GerenciadorProjetos.Entity.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<ProjectMember,Long> {
}
