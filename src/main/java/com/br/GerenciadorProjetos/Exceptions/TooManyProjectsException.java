package com.br.GerenciadorProjetos.Exceptions;

public class TooManyProjectsException extends RuntimeException {
    public TooManyProjectsException(String message) {
        super(message);
    }
}
