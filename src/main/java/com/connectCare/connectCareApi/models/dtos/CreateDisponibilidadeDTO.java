package com.connectCare.connectCareApi.models.dtos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class CreateDisponibilidadeDTO {

    private LocalDate dataDisponivel;
    private LocalTime horarioDisponivel;
    private boolean agendado;
    private Integer idMedico;

    public CreateDisponibilidadeDTO() {
    }

    public CreateDisponibilidadeDTO(LocalDate dataDisponivel, LocalTime horarioDisponivel, boolean agendado, Integer idMedico) {
        this.dataDisponivel = dataDisponivel;
        this.horarioDisponivel = horarioDisponivel;
        this.agendado = agendado;
        this.idMedico = idMedico;
    }

    public LocalDate getDataDisponivel() {
        return dataDisponivel;
    }

    public void setDataDisponivel(LocalDate dataDisponivel) {
        this.dataDisponivel = dataDisponivel;
    }

    public LocalTime getHorarioDisponivel() {
        return horarioDisponivel;
    }

    public void setHorarioDisponivel(LocalTime horarioDisponivel) {
        this.horarioDisponivel = horarioDisponivel;
    }

    public boolean isAgendado() {
        return agendado;
    }

    public void setAgendado(boolean agendado) {
        this.agendado = agendado;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateDisponibilidadeDTO that = (CreateDisponibilidadeDTO) o;
        return agendado == that.agendado && Objects.equals(dataDisponivel, that.dataDisponivel) && Objects.equals(horarioDisponivel, that.horarioDisponivel) && Objects.equals(idMedico, that.idMedico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataDisponivel, horarioDisponivel, agendado, idMedico);
    }
}
