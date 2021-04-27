package com.compasso.academia.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compasso.academia.model.Aula;

@Repository
@Transactional
public interface AulaRepository extends JpaRepository<Aula, Long>{
    
}
