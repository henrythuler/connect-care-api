package com.connectCare.connectCareApi.models.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class CreateConsultaDTO {

    @NotNull(message = "Forma de Agendamento é obrigatória!")
    private String formaAgendamento;

    @NotNull(message = "Tipo da Consulta é obrigatória!")
    private String tipoConsulta;

    @NotNull(message = "ID do médico é obrigatório!")
    private Integer idMedico;

    @NotNull(message = "ID do paciente é obrigatório!")
    private Integer idPaciente;

    @NotNull(message = "ID da disponibilidade é obrigatório!")
    private Integer idDisponibilidade;

    public CreateConsultaDTO() {
    }

    public CreateConsultaDTO(String formaAgendamento, String tipoConsulta, Integer idMedico, Integer idPaciente, Integer idDisponibilidade) {
        this.formaAgendamento = formaAgendamento;
        this.tipoConsulta = tipoConsulta;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.idDisponibilidade = idDisponibilidade;
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

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdDisponibilidade() {
        return idDisponibilidade;
    }

    public void setIdDisponibilidade(Integer idDisponibilidade) {
        this.idDisponibilidade = idDisponibilidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateConsultaDTO that = (CreateConsultaDTO) o;
        return Objects.equals(formaAgendamento, that.formaAgendamento) && Objects.equals(tipoConsulta, that.tipoConsulta) && Objects.equals(idMedico, that.idMedico) && Objects.equals(idPaciente, that.idPaciente) && Objects.equals(idDisponibilidade, that.idDisponibilidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formaAgendamento, tipoConsulta, idMedico, idPaciente, idDisponibilidade);
    }
}
