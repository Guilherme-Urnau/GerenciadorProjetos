package com.br.GerenciadorProjetos.Dtos;

import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Enums.MemberRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberRequestDto(

        @NotBlank
        String name,

        @NotBlank
        String role

) {  }