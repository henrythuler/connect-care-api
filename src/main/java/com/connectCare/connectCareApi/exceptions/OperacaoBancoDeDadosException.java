package com.connectCare.connectCareApi.exceptions;

public class OperacaoBancoDeDadosException extends RuntimeException{

    public OperacaoBancoDeDadosException() {
        super("Ocorreu um erro na operação no banco de dados. Tente novamente...");
    }

    public OperacaoBancoDeDadosException(String message) {
        super(message);
    }
}
