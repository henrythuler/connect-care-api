package com.connectCare.connectCareApi.models.dtos;

import com.connectCare.connectCareApi.models.entities.Especialidade;
import com.connectCare.connectCareApi.models.entities.Medico;
import com.connectCare.connectCareApi.models.superclasses.Pessoa;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class GetMedicoDTO extends Pessoa {

    private String crm;
    private BigDecimal valorDaConsulta;
    private boolean presencial;
    private boolean teleconsulta;

    private Especialidade especialidade;

    public GetMedicoDTO() {
    }

    public GetMedicoDTO(Medico medico) {
        super(medico.getId(), medico.getNome(), medico.getDataNascimento(), medico.getGenero(), medico.getEndereco(), medico.getTelefone());
        this.crm = medico.getCrm();
        this.valorDaConsulta = medico.getValorDaConsulta();
        this.presencial = medico.getPresencial();
        this.teleconsulta = medico.getTeleconsulta();
        this.especialidade = medico.getEspecialidade();
    }

    public GetMedicoDTO(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String crm, BigDecimal valorDaConsulta, boolean presencial, boolean teleconsulta, Especialidade especialidade) {
        super(id, nome, dataNascimento, genero, endereco, telefone);
        this.crm = crm;
        this.valorDaConsulta = valorDaConsulta;
        this.presencial = presencial;
        this.teleconsulta = teleconsulta;
        this.especialidade = especialidade;
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

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GetMedicoDTO that = (GetMedicoDTO) o;
        return presencial == that.presencial && teleconsulta == that.teleconsulta && Objects.equals(crm, that.crm) && Objects.equals(valorDaConsulta, that.valorDaConsulta) && Objects.equals(especialidade, that.especialidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crm, valorDaConsulta, presencial, teleconsulta, especialidade);
    }
}
