package com.connectCare.connectCareApi.models.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;
import java.util.Objects;

@Entity
@DiscriminatorValue("Dependente")
public class Dependente extends Paciente {

    @ManyToOne
    @JoinColumn(name = "id_responsavel")
    private Paciente responsavel;

    public Dependente() {}

    public Dependente(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String cpf, Paciente responsavel) {
        super(id, nome, dataNascimento, genero, endereco, telefone, cpf,null);
        this.responsavel = responsavel;
    }

    public Paciente getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Paciente responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Dependente that = (Dependente) o;
        return Objects.equals(responsavel, that.responsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), responsavel);
    }
}
