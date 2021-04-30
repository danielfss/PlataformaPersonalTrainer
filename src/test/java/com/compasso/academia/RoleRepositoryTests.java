package com.compasso.academia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.compasso.academia.model.Role;
import com.compasso.academia.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	@Autowired RoleRepository repo;
	
	@Test
	public void testCreateRoles() {
		Role admin = new Role("PERSONAL");
		Role user = new Role("ALUNO");
<<<<<<< HEAD
		
=======
>>>>>>> 0cabd928df3eb70e504dad8ec5f66fe597bada29
		
		repo.saveAll(List.of(admin, user));
		
		List<Role> listRoles = repo.findAll();
		
		assertThat(listRoles.size()).isEqualTo(2);
	}
}
