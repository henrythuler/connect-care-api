package com.connectCare.connectCareApi.exceptions;

public class EspecialidadeNaoEncontradaException extends RuntimeException{

    public EspecialidadeNaoEncontradaException(Integer id) {
        super("Especialidade com ID " + id + " não encontrada...");
    }
}
