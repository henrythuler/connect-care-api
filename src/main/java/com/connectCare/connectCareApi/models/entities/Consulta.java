package com.connectCare.connectCareApi.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_consulta")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String formaAgendamento;
	private String tipoConsulta;

	@ManyToOne
	@JoinColumn(name="id_medico")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name="id_paciente")
	private Paciente paciente;

	@OneToOne
	@JoinColumn(name="id_disponibilidade")
	private Disponibilidade disponibilidade;

	public Consulta() {}

	public Consulta(Integer id, String formaAgendamento, String tipoConsulta, Medico medico, Paciente paciente,
			Disponibilidade disponibilidade) {
		super();
		this.id = id;
		this.formaAgendamento = formaAgendamento;
		this.tipoConsulta = tipoConsulta;
		this.medico = medico;
		this.paciente = paciente;
		this.disponibilidade = disponibilidade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Disponibilidade getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Disponibilidade disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((disponibilidade == null) ? 0 : disponibilidade.hashCode());
		result = prime * result + ((formaAgendamento == null) ? 0 : formaAgendamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((tipoConsulta == null) ? 0 : tipoConsulta.hashCode());
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
		Consulta other = (Consulta) obj;
		if (disponibilidade == null) {
			if (other.disponibilidade != null)
				return false;
		} else if (!disponibilidade.equals(other.disponibilidade))
			return false;
		if (formaAgendamento == null) {
			if (other.formaAgendamento != null)
				return false;
		} else if (!formaAgendamento.equals(other.formaAgendamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (tipoConsulta == null) {
			if (other.tipoConsulta != null)
				return false;
		} else if (!tipoConsulta.equals(other.tipoConsulta))
			return false;
		return true;
	}
		
}
