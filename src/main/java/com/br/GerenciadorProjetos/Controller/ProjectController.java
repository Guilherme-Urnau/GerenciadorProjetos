package com.br.GerenciadorProjetos.Controller;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectFilterDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping
    public Page<ProjectResponseDto> getAllWithFilters(ProjectFilterDto filter, @PageableDefault Pageable pageable){
        service.getProject(filter,pageable);
        return null; //todo
    }

    @PutMapping
    public ProjectResponseDto createProject(@Valid @RequestBody ProjectRequestDto dto){
        service.createProject(dto);

        return null; //todo
    }

    @PostMapping
    public ProjectResponseDto updatProject(@Valid @RequestBody ProjectRequestDto dto){
        return null; //todo
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId){
        return; //todo
    }

}
