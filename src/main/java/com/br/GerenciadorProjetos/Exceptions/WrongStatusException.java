package com.br.GerenciadorProjetos.Exceptions;

public class WrongStatusException extends RuntimeException {
    public WrongStatusException(String message) {
        super(message);
    }
}
