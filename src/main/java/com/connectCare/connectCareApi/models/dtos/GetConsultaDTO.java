package com.connectCare.connectCareApi.models.dtos;

import com.connectCare.connectCareApi.models.entities.Consulta;

import java.util.Objects;

public class GetConsultaDTO {

    private String formaAgendamento;
    private String tipoConsulta;

    private GetPacienteDTO paciente;

    private GetDisponibilidadeDTO disponibilidade;

    public GetConsultaDTO() {
    }

    public GetConsultaDTO(String formaAgendamento, String tipoConsulta, GetPacienteDTO paciente, GetDisponibilidadeDTO disponibilidade) {
        this.formaAgendamento = formaAgendamento;
        this.tipoConsulta = tipoConsulta;
        this.paciente = paciente;
        this.disponibilidade = disponibilidade;
    }

    public GetConsultaDTO(Consulta consulta) {
        this.formaAgendamento = consulta.getFormaAgendamento();
        this.tipoConsulta = consulta.getTipoConsulta();
        this.paciente = new GetPacienteDTO(consulta.getPaciente());
        this.disponibilidade = new GetDisponibilidadeDTO(consulta.getDisponibilidade());
    }

    public String getFormaAgendamento() {
        return formaAgendamento;
    }

    public void setFormaAgendamento(String formaAgendamento) {
        this.formaAgendamento = formaAgendamento;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public GetPacienteDTO getPaciente() {
        return paciente;
    }

    public void setPaciente(GetPacienteDTO paciente) {
        this.paciente = paciente;
    }

    public GetDisponibilidadeDTO getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(GetDisponibilidadeDTO disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetConsultaDTO that = (GetConsultaDTO) o;
        return Objects.equals(formaAgendamento, that.formaAgendamento) && Objects.equals(tipoConsulta, that.tipoConsulta) && Objects.equals(paciente, that.paciente) && Objects.equals(disponibilidade, that.disponibilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formaAgendamento, tipoConsulta, paciente, disponibilidade);
    }
}
