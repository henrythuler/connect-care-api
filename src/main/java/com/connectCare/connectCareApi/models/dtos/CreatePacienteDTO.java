package com.connectCare.connectCareApi.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.Objects;

public class CreatePacienteDTO {

    @NotNull(message = "Nome é obrigatório!")
    @Size(min = 2, max = 255, message = "Nome deve possuir entre 2 a 255 caracteres!")
    private String nome;

    @NotNull(message = "Data de Nascimento é obrigatória!")
    private Date dataNascimento;

    @NotBlank(message = "Gênero é obrigatório!")
    @Size(min = 1, max = 1, message = "Escreva F para Feminimo ou M para Masculino")
    private char genero; //F - Feminino | M - Masculino

    @NotNull(message = "Endereço é obrigatório!")
    @Size(min = 2, max = 255, message = "Endereço deve possuir entre 2 a 255 caracteres!")
    private String endereco;

    @NotNull(message = "Telefone é obrigatório!")
    @Size(min = 8, max = 18, message = "Telefone deve possuir entre 8 a 18 caracteres!")
    private String telefone;

    @NotNull(message = "CPF é obrigatório!")
    @Size(min = 11, max = 11, message = "CPF deve possuir 11 caracteres! (Sem pontuação)")
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
