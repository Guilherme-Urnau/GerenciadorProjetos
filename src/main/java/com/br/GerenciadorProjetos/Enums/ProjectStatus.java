package com.br.GerenciadorProjetos.Enums;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Exceptions.ProjectExclusionNotAllowedException;
import com.br.GerenciadorProjetos.Exceptions.StatusMovedWronglyException;
import com.br.GerenciadorProjetos.Exceptions.WrongStatusException;
import jakarta.persistence.EntityNotFoundException;

import java.util.Arrays;

public enum ProjectStatus {

    EM_ANALIZE(1,"EM ANÁLIZE"),
    ANALIZE_REALIZADA(2,"ANÁLIZE REALIZADA"),
    ANALIZE_APROVADA(3,"ANÁLIZE APROVADA"),
    INICIADO(4,"INICIADO"),
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

    public static ProjectStatus getByOrder(Integer order) {
        return Arrays.stream(values())
                .filter(status -> status.order.equals(order))
                .findFirst()
                .orElseThrow(() -> new WrongStatusException("Status não encontrado."));
    }

    public static void validStatusChange(ProjectStatus incomingStatus, ProjectStatus persistedStatus) {
        if(incomingStatus.order == persistedStatus.order+1)
            return;
        if(incomingStatus.order == -1)
            return;

        throw new StatusMovedWronglyException();
    }

    public static void exclusionAllowed(ProjectStatus persistedStatus) {
        if(persistedStatus.order >= INICIADO.order)
            throw new ProjectExclusionNotAllowedException();
    }

}