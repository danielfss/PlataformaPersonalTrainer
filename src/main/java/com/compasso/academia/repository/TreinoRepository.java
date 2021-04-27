package com.compasso.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.academia.model.Treino;


public interface TreinoRepository extends JpaRepository<Treino, Long> {
	Treino findById(long id);
}
