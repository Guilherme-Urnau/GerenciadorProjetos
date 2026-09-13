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

    @GetMapping("/{projectId}")
    public ProjectResponseDto getProjectById(@PathVariable Long projectId) {
        ProjectResponseDto responseDto = service.getProjectById(projectId);
        return responseDto;
    }

    @GetMapping
    public Page<ProjectResponseDto> getAllWithFilters(ProjectFilterDto filter, @PageableDefault Pageable pageable){
        service.getProjectByFilter(filter,pageable);
        return null; //todo
    }

    @PutMapping
    public ProjectResponseDto createProject(@Valid @RequestBody ProjectRequestDto dto){
        ProjectResponseDto responseDto = service.createProject(dto);
        return responseDto;
    }

    @PostMapping("/{projectId}")
    public ProjectResponseDto updatProject(@PathVariable Long projectId, @Valid @RequestBody ProjectRequestDto dto){
        ProjectResponseDto responseDto = service.updateProject(projectId, dto);
        return responseDto;
    }

    @DeleteMapping("/{projectId}")
    public void deleteProject(@PathVariable Long projectId){
        service.deleteProject(projectId);
    }

}
