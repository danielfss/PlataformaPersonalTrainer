package com.compasso.academia.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.compasso.academia.model.Aula;

@Repository
@Transactional
public interface AulaRepository extends JpaRepository<Aula, Long>{
    
	@Query("SELECT a FROM Aula a WHERE a.disponibilidade = ?1")
	Aula findByDisponibilidade();
	
	Aula findById(long id);
	
}
