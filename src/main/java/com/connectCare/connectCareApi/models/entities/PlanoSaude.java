package com.connectCare.connectCareApi.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity(name = "T_PLANO_SAUDE")
public class PlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String convenio;
    private String plano;
    private String numCarteirinha;

    public PlanoSaude() {}

    public PlanoSaude(Integer id, String convenio, String plano, String numCarteirinha) {
        this.id = id;
        this.convenio = convenio;
        this.plano = plano;
        this.numCarteirinha = numCarteirinha;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoSaude that = (PlanoSaude) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
