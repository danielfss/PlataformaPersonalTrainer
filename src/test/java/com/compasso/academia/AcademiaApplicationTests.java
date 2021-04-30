package com.compasso.academia;


import com.compasso.academia.model.AlunoStatus;
import com.compasso.academia.service.TokenService;
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
public void testAddRoleToNewUser1() {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode("87654321");

	Usuario usuario = new Usuario();
	usuario.setNome("Maria Jane Doe");
	usuario.setEmail("maria@email.com");
	usuario.setSenha(encodedPassword);
	usuario.setTelefone("71 99387848");
	usuario.setEnabled(true);
	usuario.setStatus(AlunoStatus.ATIVADA);
	usuario.setToken(TokenService.generateNewToken());
  
	Role role = roleRepo.findByName("PERSONAL");
  
	usuario.addRoles(role);
  
	repoUs.save(usuario);

	}

@Test
public void testAddRoleToNewUser2() {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode("87654321");

	Usuario usuario = new Usuario();
	usuario.setNome("Fabiola Jane Doe");
	usuario.setEmail("fabi@email.com");
	usuario.setSenha(encodedPassword);
	usuario.setTelefone("71 90005400");
	usuario.setEnabled(true);
	usuario.setStatus(AlunoStatus.ATIVADA);
	usuario.setToken(TokenService.generateNewToken());
  
	Role role = roleRepo.findByName("PERSONAL");
  
	usuario.addRoles(role);
  
	repoUs.save(usuario);

	}
@Test
public void testAddRoleToNewUser3() {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode("87654321");

	Usuario usuario = new Usuario();
	usuario.setNome("Matheus John Doe");
	usuario.setEmail("mats@email.com");
	usuario.setSenha(encodedPassword);
	usuario.setTelefone("71 9007500");
	usuario.setEnabled(true);
	usuario.setStatus(AlunoStatus.ATIVADA);
	usuario.setToken(TokenService.generateNewToken());
  
	Role role = roleRepo.findByName("PERSONAL");
  
	usuario.addRoles(role);
  
	repoUs.save(usuario);

	}
@Test
public void testAddRoleToNewUser4() {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	String encodedPassword = encoder.encode("87654321");

	Usuario usuario = new Usuario();
	usuario.setNome("Patrícia Jane Doe");
	usuario.setEmail("Pats@email.com");
	usuario.setSenha(encodedPassword);
	usuario.setTelefone("71 90546400");
	usuario.setEnabled(true);
	usuario.setStatus(AlunoStatus.ATIVADA);
	usuario.setToken(TokenService.generateNewToken());
  
	Role role = roleRepo.findByName("PERSONAL");
  
	usuario.addRoles(role);
  
	repoUs.save(usuario);

	}
}