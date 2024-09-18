package com.connectCare.connectCareApi.models.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateMedicoDTO {

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
	
	@NotNull(message = "CRM é obrigatório!")
    @Size(min = 13, max = 13, message = "CRM deve possuir 13 caracteres com esse formato(CRM/SP 123456)! (Sem pontuação)")
    private String crm;
	
	@NotNull(message = "Valor da consulta é obrigatório!")
    @Size(min = 4, max = 11, message = "Valor da consulta deve possuir esse formato(100.00)!")
    private BigDecimal valorDaConsulta;
	
	@NotNull(message = "Presencial é obrigatório!")
	@Size(min = 4, max = 5, message = "Presencial deve Sim ou Não!")
    private boolean presencial;
	
	@NotNull(message = "Teleconsulta é obrigatório!")
	@Size(min = 4, max = 5, message = "Presencial deve Sim ou Não!")
    private boolean teleconsulta;
	
    private Integer idUsuario;
    private Integer idEspecialidade;

    public CreateMedicoDTO() {
    }

    public CreateMedicoDTO(String nome, Date dataNascimento, char genero, String endereco, String telefone, String crm, BigDecimal valorDaConsulta, boolean presencial, boolean teleconsulta, Integer idUsuario, Integer idEspecialidade) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.endereco = endereco;
        this.telefone = telefone;
        this.crm = crm;
        this.valorDaConsulta = valorDaConsulta;
        this.presencial = presencial;
        this.teleconsulta = teleconsulta;
        this.idUsuario = idUsuario;
        this.idEspecialidade = idEspecialidade;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public BigDecimal getValorDaConsulta() {
        return valorDaConsulta;
    }

    public void setValorDaConsulta(BigDecimal valorDaConsulta) {
        this.valorDaConsulta = valorDaConsulta;
    }

    public boolean isPresencial() {
        return presencial;
    }

    public void setPresencial(boolean presencial) {
        this.presencial = presencial;
    }

    public boolean isTeleconsulta() {
        return teleconsulta;
    }

    public void setTeleconsulta(boolean teleconsulta) {
        this.teleconsulta = teleconsulta;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateMedicoDTO that = (CreateMedicoDTO) o;
        return genero == that.genero && presencial == that.presencial && teleconsulta == that.teleconsulta && Objects.equals(nome, that.nome) && Objects.equals(dataNascimento, that.dataNascimento) && Objects.equals(endereco, that.endereco) && Objects.equals(telefone, that.telefone) && Objects.equals(crm, that.crm) && Objects.equals(valorDaConsulta, that.valorDaConsulta) && Objects.equals(idUsuario, that.idUsuario) && Objects.equals(idEspecialidade, that.idEspecialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataNascimento, genero, endereco, telefone, crm, valorDaConsulta, presencial, teleconsulta, idUsuario, idEspecialidade);
    }
}
