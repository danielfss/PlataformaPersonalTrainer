package com.compasso.academia.CriaTabela;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.compasso.academia.model.Arquivo;
import com.compasso.academia.model.Treino;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.model.Video;

public class CT_Treino 
{
    public static void main(String[] args) 
    {
        Usuario usuario = new Usuario();
        usuario.setId(1L);

        Video video = new Video();
        video.setUrl("google.com");

        Arquivo arquivo = new Arquivo();
        arquivo.setUrl("google.com");

        Treino treino = new Treino();
        treino.setTitulo("Treino 1");
        treino.setDescricao("Testando o primeiro treino");
        treino.setArquivo(arquivo);
        treino.setVideo(video);
        treino.setUsuario(Arrays.asList(usuario));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("personaltrainer");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(video);
        em.persist(arquivo);
        em.persist(treino);

        em.getTransaction().commit();
        em.close();
    }
}
