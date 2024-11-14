package com.connectCare.connectCareApi.models.dtos;

import com.connectCare.connectCareApi.models.entities.Disponibilidade;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class GetDisponibilidadeDTO {

    private Integer id;
    private LocalDate dataDisponivel;
    private LocalTime horarioDisponivel;
    private boolean agendado;

    private GetMedicoDTO medico;

    public GetDisponibilidadeDTO() {
    }

    public GetDisponibilidadeDTO(Integer id, LocalDate dataDisponivel, LocalTime horarioDisponivel, boolean agendado, GetMedicoDTO medico) {
        this.id = id;
        this.dataDisponivel = dataDisponivel;
        this.horarioDisponivel = horarioDisponivel;
        this.agendado = agendado;
        this.medico = medico;
    }

    public GetDisponibilidadeDTO(Disponibilidade disponibilidade) {
        this.id = disponibilidade.getId();
        this.dataDisponivel = disponibilidade.getDataDisponivel();
        this.horarioDisponivel = disponibilidade.getHorarioDisponivel();
        this.agendado = disponibilidade.isAgendado();
        this.medico = new GetMedicoDTO(disponibilidade.getMedico());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public GetMedicoDTO getMedico() {
        return medico;
    }

    public void setMedico(GetMedicoDTO medico) {
        this.medico = medico;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetDisponibilidadeDTO that = (GetDisponibilidadeDTO) o;
        return agendado == that.agendado && Objects.equals(id, that.id) && Objects.equals(dataDisponivel, that.dataDisponivel) && Objects.equals(horarioDisponivel, that.horarioDisponivel) && Objects.equals(medico, that.medico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dataDisponivel, horarioDisponivel, agendado, medico);
    }
}
