package com.br.GerenciadorProjetos.configs;

import com.br.GerenciadorProjetos.Exceptions.WrongRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(WrongRoleException.class)
    public ResponseEntity<String> handleWrongRoleException(WrongRoleException ex){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
    }



}
