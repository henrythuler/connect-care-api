package com.connectCare.connectCareApi.exceptions;

public class UsuarioNaoEncontradoException extends RuntimeException{

    public UsuarioNaoEncontradoException(Integer id) {
        super("Usuário com ID " + id + " não encontrado...");
    }
}
