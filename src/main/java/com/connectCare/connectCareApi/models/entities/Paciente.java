package com.connectCare.connectCareApi.models.entities;

import com.connectCare.connectCareApi.models.superclasses.Pessoa;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity(name = "T_PACIENTE")
public class Paciente extends Pessoa {

    private String cpf; //11 Dígitos - Sem pontuação

    public Paciente(){}

    public Paciente(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String cpf) {
        super(id, nome, dataNascimento, genero, endereco, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
