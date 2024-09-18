package com.connectCare.connectCareApi.exceptions;

public class ConsultaNaoEncontradaException extends RuntimeException{

    public ConsultaNaoEncontradaException(Integer id) {
        super("Consulta com ID " + id + " não encontrada...");
    }
}
