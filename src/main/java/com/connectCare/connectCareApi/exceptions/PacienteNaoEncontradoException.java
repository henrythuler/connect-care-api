package com.connectCare.connectCareApi.exceptions;

public class PacienteNaoEncontradoException extends RuntimeException{

    public PacienteNaoEncontradoException(Integer id) {
        super("Paciente com ID " + id + " não encontrado...");
    }

}
