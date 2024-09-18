package com.connectCare.connectCareApi.exceptions;

public class DisponibilidadeNaoEncontradaException extends RuntimeException{

    public DisponibilidadeNaoEncontradaException(Integer id) {
        super("Disponibilidade com ID " + id + " não encontrada...");
    }

}
