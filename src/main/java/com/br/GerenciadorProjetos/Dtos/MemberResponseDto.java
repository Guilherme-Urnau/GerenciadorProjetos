package com.br.GerenciadorProjetos.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MemberResponseDto(

        Long id,

        String name,

        String role,

        List<Long> projectIds

) {  }