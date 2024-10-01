package com.connectCare.connectCareApi.models.dtos;

import com.connectCare.connectCareApi.models.entities.Paciente;
import com.connectCare.connectCareApi.models.superclasses.Pessoa;

import java.util.Date;
import java.util.Objects;

public class GetPacienteDTO extends Pessoa {

    private String cpf;

    public GetPacienteDTO() {
    }

    public GetPacienteDTO(Paciente paciente) {
        super(paciente.getId(), paciente.getNome(), paciente.getDataNascimento(), paciente.getGenero(), paciente.getEndereco(), paciente.getTelefone());
        this.cpf = paciente.getCpf();
    }

    public GetPacienteDTO(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String cpf) {
        super(id, nome, dataNascimento, genero, endereco, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GetPacienteDTO that = (GetPacienteDTO) o;
        return Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf);
    }
}
