package com.br.GerenciadorProjetos.Enums;

import com.br.GerenciadorProjetos.Entity.Project;

public enum ProjectStatus {

    EM_ANALIZE(1,"EM ANÁLIZE"),
    ANALIZE_REALIZADA(2,"ANÁLIZE REALIZADA"),
    ANALIZE_APROVADA(3,"ANÁLIZE APROVADA"),
    INICIANDO(4,"INICIADO"),
    PLANEJANDO(5,"PLANEJADO"),
    EM_ANDAMENTO(6,"EM ANDAMENTO"),
    ENCERRADO(7,"ENCERRADO"),
    CANCELADO(-1,"CANCELADO");

    private final Integer order;
    private final String description;

    ProjectStatus(int order, String description) {
        this.order = order;
        this.description = description;
    }

}
