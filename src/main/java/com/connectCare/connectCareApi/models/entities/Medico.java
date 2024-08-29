package com.connectCare.connectCareApi.models.entities;

import java.io.Serializable;
import java.util.Date;

import com.connectCare.connectCareApi.models.superclasses.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_medico")
public class Medico extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String crm;
    private String valorDaConsulta;
    private char presencial;
    
	public Medico() {
		super();
	}

	public Medico(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String crm, String valorDaConsulta, char presencial) {
		super(id, nome, dataNascimento, genero, endereco, telefone);
        this.crm = crm;
        this.valorDaConsulta = valorDaConsulta;
        this.presencial = presencial;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	

	public String getValorDaConsulta() {
		return valorDaConsulta;
	}

	public void setValorDaConsulta(String valorDaConsulta) {
		this.valorDaConsulta = valorDaConsulta;
	}

	public char getPresencial() {
		return presencial;
	}

	public void setPresencial(char presencial) {
		this.presencial = presencial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
		result = prime * result + presencial;
		result = prime * result + ((valorDaConsulta == null) ? 0 : valorDaConsulta.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (crm == null) {
			if (other.crm != null)
				return false;
		} else if (!crm.equals(other.crm))
			return false;
		if (presencial != other.presencial)
			return false;
		if (valorDaConsulta == null) {
			if (other.valorDaConsulta != null)
				return false;
		} else if (!valorDaConsulta.equals(other.valorDaConsulta))
			return false;
		return true;
	}	
    
}
