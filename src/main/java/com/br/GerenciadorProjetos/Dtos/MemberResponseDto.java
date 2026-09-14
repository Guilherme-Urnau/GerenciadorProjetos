package com.br.GerenciadorProjetos.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MemberResponseDto(

        Long id,

        String name,

        Long projectId,

        String memberRole

) {  }