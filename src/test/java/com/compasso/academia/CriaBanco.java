package com.compasso.academia;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CriaBanco 
{
    public static void main(String[] args) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        emf.createEntityManager();
    }
}
