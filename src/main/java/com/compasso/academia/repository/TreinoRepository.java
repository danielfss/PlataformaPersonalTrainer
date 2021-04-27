package com.compasso.personaltrainer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.personaltrainer.model.Treino;


public interface TreinoRepository extends JpaRepository<Treino, Long> {
	Treino findById(long id);
}
