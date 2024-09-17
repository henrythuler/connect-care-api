package com.connectCare.connectCareApi.exceptions;

public class NenhumRegistroEncontradoException extends RuntimeException{

    public NenhumRegistroEncontradoException(String entidade) {
        super("Nenhum(a) " + entidade + " foi encontrado...");
    }
}
