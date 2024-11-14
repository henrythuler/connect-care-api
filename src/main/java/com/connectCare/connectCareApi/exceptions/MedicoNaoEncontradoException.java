package com.connectCare.connectCareApi.exceptions;

public class MedicoNaoEncontradoException extends RuntimeException{

    public MedicoNaoEncontradoException(Integer id) {
        super("Médico com ID " + id + " não encontrado...");
    }

}
