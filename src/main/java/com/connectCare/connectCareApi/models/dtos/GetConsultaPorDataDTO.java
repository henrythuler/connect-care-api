package com.connectCare.connectCareApi.models.dtos;

import java.time.LocalDate;
import java.util.Objects;

public class GetConsultaPorDataDTO {

    LocalDate start;
    LocalDate end;

    public GetConsultaPorDataDTO() {
    }

    public GetConsultaPorDataDTO(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetConsultaPorDataDTO that = (GetConsultaPorDataDTO) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
