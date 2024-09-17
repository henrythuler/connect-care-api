package com.connectCare.connectCareApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OperacaoBancoDeDadosException.class)
    public ResponseEntity<String> handleOperacaoBancoDeDadosException(OperacaoBancoDeDadosException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NenhumRegistroEncontradoException.class)
    public ResponseEntity<String> handleNenhumRegistroEncontradoException(NenhumRegistroEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteNaoEncontradoException.class)
    public ResponseEntity<String> handlePacienteNaoEncontradoException(PacienteNaoEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
