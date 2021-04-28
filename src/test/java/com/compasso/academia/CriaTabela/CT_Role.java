package com.compasso.academia.CriaTabela;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.compasso.academia.model.Role;

public class CT_Role 
{
    public static void main(String[] args) 
    {
        Role role = new Role();
        role.setNome("PERSONAL");
        Role role2 = new Role();
        role2.setNome("ALUNO");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(role);
        em.persist(role2);

        em.getTransaction().commit();
        em.close();
    }
}
