package com.compasso.academia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.compasso.academia.model.Treino;
import com.compasso.academia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u WHERE u.id = ?1")
	Usuario findPorId(long l);
	
	/* List<Usuario> findByTreino(Treino treino); */

}
