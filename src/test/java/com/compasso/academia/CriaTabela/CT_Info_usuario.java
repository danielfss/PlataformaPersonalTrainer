package com.compasso.academia.CriaTabela;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.compasso.academia.model.Info_usuario;
import com.compasso.academia.model.Usuario;

public class CT_Info_usuario 
{
    public static void main(String[] args) 
    {
        Usuario u = new Usuario();
        u.setId(1L);

        Info_usuario usuario = new Info_usuario();
        usuario.setNomeCompleto("nomePersonal");
        usuario.setEmail("personal@personal.com");
        usuario.setTelefone("(99) 9 9999-9999");
        usuario.setUsuario(u);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(usuario);

        em.getTransaction().commit();
        em.close();
    }
}
