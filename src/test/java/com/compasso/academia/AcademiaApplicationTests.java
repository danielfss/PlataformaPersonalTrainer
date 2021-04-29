package com.compasso.academia;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.compasso.academia.model.Role;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.RoleRepository;
import com.compasso.academia.repository.UsuarioRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class AcademiaApplicationTests {

	@Autowired 
	RoleRepository roleRepo;
	
	@Autowired 
	UsuarioRepository repoUs;
	
	
    @Test
    void contextLoads() {
    }



@Test
public void testAddRoleToNewUser() {
	
	String senha = "87654321";
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode(senha);
	
	Usuario usuario = new Usuario();
	usuario.setNome("Daniel");
	usuario.setEmail("dev@email.com");
	usuario.setSenha(encodedPassword);
	usuario.setTelefone("719000000");
	usuario.setEnabled(true);
  
	Role role = roleRepo.findByName("PERSONAL");
  
	usuario.addRoles(role);
  
	repoUs.save(usuario);

	}
}