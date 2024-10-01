package com.connectCare.connectCareApi.models.dtos;

import com.connectCare.connectCareApi.models.entities.Dependente;

import java.util.Objects;

public class GetDependenteDTO extends GetPacienteDTO{

    private GetPacienteDTO responsavel;

    public GetDependenteDTO() {
    }

    public GetDependenteDTO(Dependente dependente) {
        super(dependente);
        this.responsavel = new GetPacienteDTO(dependente.getResponsavel());
    }

    public GetPacienteDTO getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(GetPacienteDTO responsavel) {
        this.responsavel = responsavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GetDependenteDTO that = (GetDependenteDTO) o;
        return Objects.equals(responsavel, that.responsavel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), responsavel);
    }
}
