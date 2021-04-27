package com.compasso.academia.CriaTabela;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.compasso.academia.model.Role;
import com.compasso.academia.model.Usuario;

import java.util.Arrays;

public class CT_Usuario
{   

    public static void main(String[] args) 
    {
        Role role = new Role();
        role.setId(1L);

        Usuario usuario = new Usuario();
        usuario.setNome("testePersonal");
        usuario.setSenha("123");
        usuario.setEmail("personal@email.com");
        usuario.setTelefone("71 9 9999-9999");
        usuario.setEnabled(true);
        usuario.setRoles(Arrays.asList(role));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();
    }
}
