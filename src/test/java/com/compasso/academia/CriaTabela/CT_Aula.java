package com.compasso.academia.CriaTabela;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.compasso.academia.model.Aula;
import com.compasso.academia.model.Usuario;

public class CT_Aula 
{
    /*public static void main(String[] args) 
    {
        Usuario usuarioPersonal = new Usuario();
        usuarioPersonal.setId(1L);

        Aula aula = new Aula();
        aula.setDisponibilidade(LocalDateTime.now());
        aula.setPersonal(usuarioPersonal);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(aula);

        em.getTransaction().commit();
        em.close();
    }
    */
}
