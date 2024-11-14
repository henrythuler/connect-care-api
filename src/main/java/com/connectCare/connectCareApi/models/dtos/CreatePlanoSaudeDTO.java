package com.connectCare.connectCareApi.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreatePlanoSaudeDTO {
	
	private Integer id;
	
	@NotNull(message = "Convenio é obrigatório!")
    @Size(min = 2, max = 255, message = "Convenio deve possuir entre 2 a 255 caracteres!")
    private String convenio;
	
	@NotNull(message = "Plano é obrigatório!")
    @Size(min = 2, max = 255, message = "Plano deve possuir entre 2 a 255 caracteres!")
    private String plano;
	
	@NotNull(message = "Numero da Carteirinha é obrigatório!")
    @Size(min = 8, max = 16, message = "Número da Carteirinha deve possuir entre 8 a 16 caracteres!")
    private String numCarteirinha;
	
	@NotNull(message = "Paciente é obrigatório!")
    private Integer idPaciente;
    
	public CreatePlanoSaudeDTO() {}

	public CreatePlanoSaudeDTO(Integer id, String convenio, String plano, String numCarteirinha, Integer idPaciente) {
		this.id = id;
		this.convenio = convenio;
		this.plano = plano;
		this.numCarteirinha = numCarteirinha;
		this.idPaciente = idPaciente;
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

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((convenio == null) ? 0 : convenio.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
		result = prime * result + ((numCarteirinha == null) ? 0 : numCarteirinha.hashCode());
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
		CreatePlanoSaudeDTO other = (CreatePlanoSaudeDTO) obj;
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
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		if (numCarteirinha == null) {
			if (other.numCarteirinha != null)
				return false;
		} else if (!numCarteirinha.equals(other.numCarteirinha))
			return false;
		if (plano == null) {
			if (other.plano != null)
				return false;
		} else if (!plano.equals(other.plano))
			return false;
		return true;
	}
	
}
