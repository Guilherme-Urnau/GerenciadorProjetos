package com.br.GerenciadorProjetos.Controller;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Dtos.ProjectFilterDto;
import com.br.GerenciadorProjetos.Dtos.ProjectResponseDto;
import com.br.GerenciadorProjetos.Dtos.ProjectsReportDto;
import com.br.GerenciadorProjetos.Services.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;

    @GetMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponseDto getProjectById(@PathVariable Long projectId) {
        ProjectResponseDto responseDto = service.getProjectById(projectId);
        return responseDto;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ProjectResponseDto> getAllWithFilters(ProjectFilterDto filter, @PageableDefault Pageable pageable){
        return service.getProjectByFilter(filter,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponseDto createProject(@Valid @RequestBody ProjectRequestDto dto){
        ProjectResponseDto responseDto = service.createProject(dto);
        return responseDto;
    }

    @PutMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectResponseDto updateProject(@PathVariable Long projectId, @Valid @RequestBody ProjectRequestDto dto){
        ProjectResponseDto responseDto = service.updateProject(projectId, dto);
        return responseDto;
    }

    @DeleteMapping("/{projectId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProject(@PathVariable Long projectId){
        service.deleteProject(projectId);
    }

    @GetMapping("/report")
    @ResponseStatus(HttpStatus.OK)
    public ProjectsReportDto getReport(){
        return service.getReport();
    }

}
