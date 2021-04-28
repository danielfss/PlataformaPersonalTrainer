package com.compasso.academia.CriaTabela;

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
class CT_Usuario{   
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UsuarioRepository usuarioRepository;
        
       
                
    @Test
    public void testAddRole() {
    Role role = roleRepo.findByName("ALUNO");
    
    Usuario usuario = new Usuario();
    
    usuario.setNome("testePersonal");
    usuario.setSenha("123");
    usuario.setEmail("maria@email.com");
    usuario.setTelefone("71 9 9999-9999");
    usuario.setEnabled(true);
    usuario.addRoles(role);

    usuarioRepository.save(usuario);
    
    }
        
        /*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("academia");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();*/
    }
    

