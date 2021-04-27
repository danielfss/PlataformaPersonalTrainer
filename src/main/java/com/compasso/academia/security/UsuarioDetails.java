package com.compasso.academia.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.compasso.academia.model.Role;
import com.compasso.academia.model.Usuario;

public class UsuarioDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public UsuarioDetails(Usuario usuario) {
		this.usuario = usuario;	
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = usuario.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        for (Role role: roles) {
        	authorities.add(new SimpleGrantedAuthority(role.getNome()));
        }
		
        return authorities;
	}
	
	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
	    if(usuario.isEnabled() == false) return false;

	    if(!usuario.isEnabled() == true) {
	    	return false;
	    }
		return true;
	}
	
	public String getName() {
		return usuario.getNome();
	}
	
	public Long getId() {
		return usuario.getId();
	}
	
	public void setName(String name) {
		this.usuario.setNome(name);
	}
	
	public List<Role> getRoles() {

		return usuario.getRoles();
	}	

	public String getTelefone() {
		return usuario.getTelefone();
	}
	
	public String getEmail() {
		return usuario.getEmail();
	}
	/*
	 * public boolean hasRole(String roleName) { return usuario.getRoles(roleName);
	 * }
	 */
}
