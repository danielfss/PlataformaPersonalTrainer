package com.compasso.academia.dto;

import java.time.LocalDateTime;

import com.compasso.academia.model.Aula;

import org.springframework.format.annotation.DateTimeFormat;

public class AulaDto {
    
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime disponibilidade;

    public LocalDateTime getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(LocalDateTime disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Aula toAula() {
		Aula aula = new Aula();
		aula.setDisponibilidade(this.disponibilidade);

		System.out.println(aula.getDisponibilidade());
		return aula;
	}
    
}
