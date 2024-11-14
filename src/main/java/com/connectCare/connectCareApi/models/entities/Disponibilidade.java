package com.connectCare.connectCareApi.models.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="t_disponibilidade")
public class Disponibilidade {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dataDisponivel;
    private LocalTime horarioDisponivel;
    private boolean agendado;

    @ManyToOne
    @JoinColumn(name="id_medico")
    private Medico medico;

	public Disponibilidade() {}

	public Disponibilidade(Integer id, LocalDate dataDisponivel, LocalTime horarioDisponivel, boolean agendado,
			Medico medico) {
		super();
		this.id = id;
		this.dataDisponivel = dataDisponivel;
		this.horarioDisponivel = horarioDisponivel;
		this.agendado = agendado;
		this.medico = medico;
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (agendado ? 1231 : 1237);
		result = prime * result + ((dataDisponivel == null) ? 0 : dataDisponivel.hashCode());
		result = prime * result + ((horarioDisponivel == null) ? 0 : horarioDisponivel.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
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
		Disponibilidade other = (Disponibilidade) obj;
		if (agendado != other.agendado)
			return false;
		if (dataDisponivel == null) {
			if (other.dataDisponivel != null)
				return false;
		} else if (!dataDisponivel.equals(other.dataDisponivel))
			return false;
		if (horarioDisponivel == null) {
			if (other.horarioDisponivel != null)
				return false;
		} else if (!horarioDisponivel.equals(other.horarioDisponivel))
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
		return true;
	}
	
}
