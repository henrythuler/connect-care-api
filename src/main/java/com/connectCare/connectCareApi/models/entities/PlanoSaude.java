package com.connectCare.connectCareApi.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity(name = "T_PLANO_SAUDE")
public class PlanoSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String convenio;
    private String plano;
    private String numCarteirinha;
    
    @OneToOne
    @JoinColumn(name="id_paciente")
    private Paciente paciente;

    public PlanoSaude() {}

    public PlanoSaude(Integer id, String convenio, String plano, String numCarteirinha, Paciente paciente) {
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
    
    public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((convenio == null) ? 0 : convenio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((numCarteirinha == null) ? 0 : numCarteirinha.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((plano == null) ? 0 : plano.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanoSaude other = (PlanoSaude) obj;
		if (convenio == null) {
			if (other.convenio != null)
				return false;
		} else if (!convenio.equals(other.convenio))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (numCarteirinha == null) {
			if (other.numCarteirinha != null)
				return false;
		} else if (!numCarteirinha.equals(other.numCarteirinha))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (plano == null) {
			if (other.plano != null)
				return false;
		} else if (!plano.equals(other.plano))
			return false;
		return true;
	}

}
