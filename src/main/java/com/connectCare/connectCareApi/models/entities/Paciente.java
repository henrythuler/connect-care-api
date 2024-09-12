package com.connectCare.connectCareApi.models.entities;

import com.connectCare.connectCareApi.models.dtos.UsuarioDTO;
import com.connectCare.connectCareApi.models.superclasses.Pessoa;
import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "T_PACIENTE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Paciente")
public class Paciente extends Pessoa {

    private String cpf; //11 Dígitos - Sem pontuação

    @OneToOne
    @JoinColumn(name="id_usuario")
    private Usuario usuario;

    public Paciente(){}

    public Paciente(Integer id, String nome, Date dataNascimento, char genero, String endereco, String telefone, String cpf, Usuario usuario) {
        super(id, nome, dataNascimento, genero, endereco, telefone);
        this.cpf = cpf;
        this.usuario = usuario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

	public UsuarioDTO getUsuario() {
		return new UsuarioDTO(usuario);
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
