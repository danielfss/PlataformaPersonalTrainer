package com.compasso.academia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.academia.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {
	Treino findById(long id);
}
