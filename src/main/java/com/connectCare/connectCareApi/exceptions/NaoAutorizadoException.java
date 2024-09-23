package com.connectCare.connectCareApi.exceptions;

public class NaoAutorizadoException extends RuntimeException{

    public NaoAutorizadoException() {
        super("Você não tem autorização para realizar essa operação...");
    }

    public NaoAutorizadoException(String message) {
        super(message);
    }
}
