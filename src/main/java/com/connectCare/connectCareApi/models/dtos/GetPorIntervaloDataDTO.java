package com.connectCare.connectCareApi.models.dtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Objects;

public class GetPorIntervaloDataDTO {

    @NotNull(message = "Data de início é obrigatória!")
    LocalDate inicio;

    @NotNull(message = "Data final é obrigatória!")
    LocalDate fim;

    public GetPorIntervaloDataDTO() {
    }

    public GetPorIntervaloDataDTO(LocalDate inicio, LocalDate fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetPorIntervaloDataDTO that = (GetPorIntervaloDataDTO) o;
        return Objects.equals(inicio, that.inicio) && Objects.equals(fim, that.fim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inicio, fim);
    }
}
