package com.compasso.academia.CriaTabela;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.compasso.academia.model.TipoUsuario;
import com.compasso.academia.model.Usuario;

public class CT_Usuario
{
    public static void main(String[] args) 
    {
        Usuario usuario = new Usuario();
        usuario.setNome("testePersonal");
        usuario.setSenha("123");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();
    }
}
