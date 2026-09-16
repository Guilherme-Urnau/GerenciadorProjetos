package com.br.GerenciadorProjetos.Exceptions;

public class StatusMovedWronglyException extends RuntimeException {
    public StatusMovedWronglyException() {
        super("Essa alteração de status não pode ser realizada. " +
                "+\n As alterações de status devem seguir a ordem determinada.");
    }
}
