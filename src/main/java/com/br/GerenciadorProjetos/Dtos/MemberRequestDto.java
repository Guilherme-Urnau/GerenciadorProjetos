package com.br.GerenciadorProjetos.Dtos;

import jakarta.validation.constraints.NotBlank;

public record MemberRequestDto(

        @NotBlank
        String name,

        @NotBlank
        String role,

        Long project

) {  }