package com.compasso.academia.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Aulas")
public class Aula 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime disponibilidade;

    @JoinColumn(unique = true, nullable = true)
    @OneToOne
    private Usuario aluno;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(LocalDateTime disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Usuario getAluno() {
        return aluno;
    }

    public void setAluno(Usuario aluno) {
        this.aluno = aluno;
    }

}
