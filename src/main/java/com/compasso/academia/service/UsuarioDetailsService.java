package com.compasso.personaltrainer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.compasso.personaltrainer.model.Usuario;
import com.compasso.personaltrainer.repository.UsuarioRepository;
import com.compasso.personaltrainer.security.UsuarioDetails;

public class UsuarioDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repo.findByEmail(email);
		if (usuario != null) {
			return new UsuarioDetails(usuario);			
		}
		
		throw new UsernameNotFoundException("Não foi encontrado usuário com email: " + email);
		
	}
}
