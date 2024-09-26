package com.connectCare.connectCareApi.models.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class LoginDTO {

    @NotNull(message = "Email é obrigatório!")
    @Size(min = 6, max = 128, message = "Email deve conter entre 6 e 128 caracteres")
    private String email;

    @NotNull(message = "Password é obrigatória!")
    @Size(min = 6, max = 24, message = "Password deve conter entre 2 e 24 caracteres")
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(email, loginDTO.email) && Objects.equals(password, loginDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
