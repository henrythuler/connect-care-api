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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NaoAutorizadoException.class)
    public ResponseEntity<String> handleNaoAutorizadoException(NaoAutorizadoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NenhumRegistroEncontradoException.class)
    public ResponseEntity<String> handleNenhumRegistroEncontradoException(NenhumRegistroEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteNaoEncontradoException.class)
    public ResponseEntity<String> handlePacienteNaoEncontradoException(PacienteNaoEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DisponibilidadeNaoEncontradaException.class)
    public ResponseEntity<String> handleDisponibilidadeNaoEncontradaException(DisponibilidadeNaoEncontradaException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EspecialidadeNaoEncontradaException.class)
    public ResponseEntity<String> handleEspecialidadeNaoEncontradaException(EspecialidadeNaoEncontradaException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConsultaNaoEncontradaException.class)
    public ResponseEntity<String> handleConsultaNaoEncontradaException(ConsultaNaoEncontradaException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DependenteNaoEncontradoException.class)
    public ResponseEntity<String> handleDependenteNaoEncontradoException(DependenteNaoEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MedicoNaoEncontradoException.class)
    public ResponseEntity<String> handleMedicoNaoEncontradoException(MedicoNaoEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlanoSaudeNaoEncontradoException.class)
    public ResponseEntity<String> handlePlanoSaudeNaoEncontradoException(PlanoSaudeNaoEncontradoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HorarioAgendadoException.class)
    public ResponseEntity<String> handleHorarioAgendadoException(HorarioAgendadoException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
