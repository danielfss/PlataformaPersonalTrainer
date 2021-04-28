package com.compasso.academia;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
	Usuario usuario = new Usuario();
	usuario.setNome("Alex");
	usuario.setEmail("alex@email.com");
	usuario.setSenha("87654321");
	usuario.setTelefone("719000000");
	usuario.setEnabled(true);
  
	Role role = roleRepo.findByName("PERSONAL"); 
  
	usuario.addRoles(role);
  
	repoUs.save(usuario);

	}
}