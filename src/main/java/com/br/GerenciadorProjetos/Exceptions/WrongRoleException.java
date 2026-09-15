package com.br.GerenciadorProjetos.Exceptions;

public class WrongRoleException extends RuntimeException {
    public WrongRoleException(String message) {
        super(message);
    }
}
