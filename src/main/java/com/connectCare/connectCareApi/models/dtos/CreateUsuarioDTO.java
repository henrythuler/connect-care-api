package com.connectCare.connectCareApi.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUsuarioDTO {
	@NotNull(message = "Email é obrigatório!")
	@Size(min = 6, max = 128, message = "Email deve conter entre 6 e 128 caracteres")
	private String email;

	@NotNull(message = "Password é obrigatória!")
	@Size(min = 6, max = 24, message = "Password deve conter entre 6 e 24 caracteres")
	private String password;

	@NotNull(message = "Role é obrigatória! (Paciente ou Médico)")
	private String role;

	public CreateUsuarioDTO() {}

	public CreateUsuarioDTO(String email, String password, String role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
