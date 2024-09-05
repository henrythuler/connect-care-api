package com.connectCare.connectCareApi.models.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.connectCare.connectCareApi.models.superclasses.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_medico")
public class Medico extends Pessoa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    private String crm;
    private BigDecimal valorDaConsulta;
    private boolean presencial;
    private boolean teleconsulta;
    
    @OneToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name="id_especialidade")
    private Especialidade especialidade;
    
	public Medico() {}

	public Medico(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String crm, BigDecimal valorDaConsulta, boolean presencial, boolean teleconsulta, Usuario usuario, Especialidade especialidade) {
		super(id, nome, dataNascimento, genero, endereco, telefone);
        this.crm = crm;
        this.valorDaConsulta = valorDaConsulta;
        this.presencial = presencial;
        this.teleconsulta = teleconsulta;
        this.usuario = usuario;
        this.especialidade = especialidade;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}
	

	public BigDecimal getValorDaConsulta() {
		return valorDaConsulta;
	}

	public void setValorDaConsulta(BigDecimal valorDaConsulta) {
		this.valorDaConsulta = valorDaConsulta;
	}

	public boolean getPresencial() {
		return presencial;
	}

	public void setPresencial(boolean presencial) {
		this.presencial = presencial;
	}
	
	public boolean getTeleconsulta() {
		return teleconsulta;
	}

	public void setTeleconsulta(boolean teleconsulta) {
		this.teleconsulta = teleconsulta;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
		result = prime * result + (presencial ? 1231 : 1237);
		result = prime * result + (teleconsulta ? 1231 : 1237);
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
		if (teleconsulta != other.teleconsulta)
			return false;
		if (valorDaConsulta == null) {
			if (other.valorDaConsulta != null)
				return false;
		} else if (!valorDaConsulta.equals(other.valorDaConsulta))
			return false;
		return true;
	}	
    
}
