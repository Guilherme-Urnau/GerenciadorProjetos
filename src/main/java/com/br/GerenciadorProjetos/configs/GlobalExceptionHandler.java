package com.br.GerenciadorProjetos.configs;

import com.br.GerenciadorProjetos.Exceptions.TooManyProjectsException;
import com.br.GerenciadorProjetos.Exceptions.WrongMemberQuantityException;
import com.br.GerenciadorProjetos.Exceptions.WrongRoleException;
import com.br.GerenciadorProjetos.Exceptions.WrongStatusException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* 409 */
    @ExceptionHandler(WrongRoleException.class)
    public ResponseEntity<String> handleWrongRoleException(WrongRoleException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(WrongMemberQuantityException.class)
    public ResponseEntity<String> handleToManyMembersException(WrongMemberQuantityException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(TooManyProjectsException.class)
    public ResponseEntity<String> handleTooManyProjectsException(TooManyProjectsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
    @ExceptionHandler(WrongStatusException.class)
    public ResponseEntity<String> handleWrongStatusException(WrongStatusException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    /* 404 */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
