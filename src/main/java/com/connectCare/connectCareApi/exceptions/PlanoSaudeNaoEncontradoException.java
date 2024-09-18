package com.connectCare.connectCareApi.exceptions;

public class PlanoSaudeNaoEncontradoException extends RuntimeException{

    public PlanoSaudeNaoEncontradoException(Integer id) {
        super("Plano de Saúde com ID " + id + " não encontrado...");
    }

}
