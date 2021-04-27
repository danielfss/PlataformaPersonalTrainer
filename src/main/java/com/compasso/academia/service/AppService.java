package com.compasso.academia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.academia.model.Treino;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.TreinoRepository;
import com.compasso.academia.repository.UsuarioRepository;

@Service
public class AppService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private TreinoRepository repoTreino;
	
	//Lista de usuarios
	public List<Usuario> getUsuarios(){
		return repo.findAll();
	}
	
	//Lista de Treinos
		public List<Treino> getTreinos(){
			return repoTreino.findAll();
		}

	//Salvar usuario
	public void saveUsuario(Usuario usuario) {
		repo.save(usuario);
	}
	
	//Delete by id
		public void delete (Long id){
			repo.deleteById(id);
		}
		
	
}
