package com.compasso.academia.dto;


import com.compasso.academia.model.Role;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.RoleRepository;
import com.compasso.academia.service.AppService;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class UsuarioDTO {
    @NotBlank(message = "Digite seu email.")
    @Email(message = "Digite um email valido!")
    private String email;

    @NotBlank
    @Length(min = 6, message = "Por favor digite seu nome completo!")
    private String nome;

    @NotBlank(message = "Precisamos de um telefone de contato valido")
    private String telefone;

    @NotBlank(message = "Digite sua senha!")
    @Length(min = 6, message = "Sua senha deve ter no minimo 6 digitos!")
    private String senha;

    @NotBlank(message = "Digite sua senha novamente!")
    private String csenha;

    public UsuarioDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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

    public void createUser(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Role roleUser =  roleRepo.findByName("ALUNO");
        Usuario user = new Usuario();


        user.setEnabled(true);
        user.addRoles(roleUser);
        user.setSenha(encoder.encode(getSenha()));
        user.setNome(getNome());
        user.setEmail(getEmail());
        user.setTelefone(getTelefone());

        service.saveUsuario(user);
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", senha='" + senha + '\'' +
                ", csenha='" + csenha + '\'' +
                '}';
    }

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private AppService service;
}