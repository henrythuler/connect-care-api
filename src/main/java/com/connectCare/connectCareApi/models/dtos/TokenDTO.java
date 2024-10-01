package com.connectCare.connectCareApi.models.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class TokenDTO {

    private String token;
    private LocalDateTime expiracao;
    private Integer idUsuario;

    public TokenDTO() {
    }

    public TokenDTO(String token, LocalDateTime expiracao, Integer idUsuario) {
        this.token = token;
        this.expiracao = expiracao;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(LocalDateTime expiracao) {
        this.expiracao = expiracao;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(token, tokenDTO.token) && Objects.equals(expiracao, tokenDTO.expiracao) && Objects.equals(idUsuario, tokenDTO.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, expiracao, idUsuario);
    }
}
