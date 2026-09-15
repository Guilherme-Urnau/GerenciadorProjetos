package com.br.GerenciadorProjetos.Repository;

import com.br.GerenciadorProjetos.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long>, JpaSpecificationExecutor<Project> {

    @Query("""
        SELECT COUNT(p) FROM Project p
        JOIN p.members m
        WHERE m.id = :memberId
        AND p.projectStatus NOT IN ("ENCERRADO","CANCELADO")
    """)
    Long isValidMember(@Param("memberId") Long memberId);

}
