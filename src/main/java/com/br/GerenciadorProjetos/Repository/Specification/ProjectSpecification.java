package com.br.GerenciadorProjetos.Repository.Specification;

import com.br.GerenciadorProjetos.Dtos.ProjectFilterDto;
import com.br.GerenciadorProjetos.Entity.Project;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {

    public static Specification<Project> entityFilters(ProjectFilterDto filter){
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.name() != null && !filter.name().isBlank()){
                String lowerCaseName = "%"+filter.name().toLowerCase()+"%";
                Predicate predicate = builder.like(builder.lower(root.get("name")), lowerCaseName);
                predicates.add(predicate);
            }

            if(filter.startDate() != null){
                Predicate predicate = builder.equal(root.get("startDate"), filter.startDate());
                predicates.add(predicate);
            }

            if(filter.estimatedEndDate() != null){
                Predicate predicate = builder.equal(root.get("estimatedEndDate"), filter.estimatedEndDate());
                predicates.add(predicate);
            }

            if(filter.actualEndDate() != null){
                Predicate predicate = builder.equal(root.get("actualEndDate"), filter.actualEndDate());
                predicates.add(predicate);
            }

            if(filter.totalBudget() != null){
                Predicate predicate = builder.equal(root.get("totalBudget"), filter.totalBudget());
                predicates.add(predicate);
            }

            if(filter.description() != null && !filter.description().isBlank()){
                String lowerCaseDescription = "%"+filter.description().toLowerCase()+"%";
                Predicate predicate = builder.like(builder.lower(root.get("description")),lowerCaseDescription);
                predicates.add(predicate);
            }

            if(filter.manager() != null){
                Predicate predicate = builder.equal(root.get("manager"), filter.manager());
                predicates.add(predicate);
            }

            if(filter.projectStatus() != null){
                Predicate predicate = builder.equal(root.get("projectStatus"), filter.projectStatus());
                predicates.add(predicate);
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));

            //todo pensar em metodo para filtrar entre datas
        };
    }

}
