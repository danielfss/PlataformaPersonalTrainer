package com.compasso.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compasso.academia.model.Aula;
import com.compasso.academia.model.Treino;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.AulaRepository;
import com.compasso.academia.repository.TreinoRepository;
import com.compasso.academia.repository.UsuarioRepository;

@Service
public class AppService {
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private TreinoRepository repoTreino;
	
	@Autowired
	private AulaRepository aulaRepository;
	
	// lista de aulas
	public List<Aula> getAulas() {
		return aulaRepository.findAll();
	}
	
	// salvar uma aula
	public void saveAula(Aula aula) {
		aulaRepository.save(aula);
	}
	
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
	
	//Delete Usuario by id
		public void delete (Long id){
			repo.deleteById(id);
		}
		
	//Delete Agendado by id
	public void deleteAgendado (Long id){
		aulaRepository.deleteById(id);
	}
	
	//Delete Agendado by id
		public void deleteAlunoTreino (Long id){
			repoTreino.deleteById(id);
		}
		
	
}