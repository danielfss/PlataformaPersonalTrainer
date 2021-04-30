package com.compasso.academia.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RecoveryDTO {
    @NotBlank(message = "Insira o token enviado para seu email.")
    String token;
    @NotBlank(message = "Insira o email usado no cadastro.")
    @Email
    String email;
    @NotBlank(message = "Insira sua nova senha")
    @Length(min = 6, message = "Sua senha deve ter no minimo 6 digitos!")
    String senha;
    @NotBlank(message = "Insira sua nova senha novamente!")
    String csenha;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCsenha() {
        return csenha;
    }

    public void setCsenha(String csenha) {
        this.csenha = csenha;
    }

    @Override
    public String     toString() {
        return "RecoveryDTO{" +
                "token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", csenha='" + csenha + '\'' +
                '}';
    }
}
