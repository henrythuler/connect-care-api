package com.connectCare.connectCareApi.exceptions;

public class DependenteNaoEncontradoException extends RuntimeException{

    public DependenteNaoEncontradoException(Integer id) {
        super("Dependente com ID " + id + " não encontrado...");
    }

}
