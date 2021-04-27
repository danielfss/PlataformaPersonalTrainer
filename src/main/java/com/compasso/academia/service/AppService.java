package com.compasso.personaltrainer.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.personaltrainer.model.Treino;
import com.compasso.personaltrainer.model.Usuario;
import com.compasso.personaltrainer.repository.TreinoRepository;
import com.compasso.personaltrainer.repository.UsuarioRepository;

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
