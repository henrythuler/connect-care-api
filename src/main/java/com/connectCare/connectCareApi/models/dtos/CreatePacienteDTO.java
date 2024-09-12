package com.connectCare.connectCareApi.models.dtos;

import java.util.Date;
import java.util.Objects;

public class CreatePacienteDTO {

    private String nome;
    private Date dataNascimento;
    private char genero; //F - Feminino | M - Masculino
    private String endereco;
    private String telefone;
    private String cpf; //11 Dígitos - Sem pontuação
    private Integer idUsuario;

    public CreatePacienteDTO() {
    }

    public CreatePacienteDTO(String nome, Date dataNascimento, char genero, String endereco, String telefone, String cpf, Integer idUsuario) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cpf = cpf;
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePacienteDTO that = (CreatePacienteDTO) o;
        return genero == that.genero && Objects.equals(nome, that.nome) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(endereco, that.endereco) && Objects.equals(telefone, that.telefone) && Objects.equals(cpf, that.cpf) && Objects.equals(idUsuario, that.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataNascimento, genero, endereco, telefone, cpf, idUsuario);
    }
}
