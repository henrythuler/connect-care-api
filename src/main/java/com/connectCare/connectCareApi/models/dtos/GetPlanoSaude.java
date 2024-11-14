package com.connectCare.connectCareApi.models.dtos;

import com.connectCare.connectCareApi.models.entities.PlanoSaude;

import java.util.Objects;

public class GetPlanoSaude {

    private Integer id;
    private String convenio;
    private String plano;
    private String numCarteirinha;

    private GetPacienteDTO paciente;

    public GetPlanoSaude() {
    }

    public GetPlanoSaude(PlanoSaude planoSaude) {
        this.id = planoSaude.getId();
        this.convenio = planoSaude.getConvenio();
        this.plano = planoSaude.getPlano();
        this.numCarteirinha = planoSaude.getNumCarteirinha();
        this.paciente = new GetPacienteDTO(planoSaude.getPaciente());
    }

    public GetPlanoSaude(Integer id, String convenio, String plano, String numCarteirinha, GetPacienteDTO paciente) {
        this.id = id;
        this.convenio = convenio;
        this.plano = plano;
        this.numCarteirinha = numCarteirinha;
        this.paciente = paciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getNumCarteirinha() {
        return numCarteirinha;
    }

    public void setNumCarteirinha(String numCarteirinha) {
        this.numCarteirinha = numCarteirinha;
    }

    public GetPacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(GetPacienteDTO paciente) {
        this.paciente = paciente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPlanoSaude that = (GetPlanoSaude) o;
        return Objects.equals(id, that.id) && Objects.equals(convenio, that.convenio) && Objects.equals(plano, that.plano) && Objects.equals(numCarteirinha, that.numCarteirinha) && Objects.equals(paciente, that.paciente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, convenio, plano, numCarteirinha, paciente);
    }
}
