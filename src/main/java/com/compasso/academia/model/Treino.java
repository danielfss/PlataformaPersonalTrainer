package com.compasso.academia.model;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Treinos")
public class Treino implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = true)
    private String titulo;
    
    @Column(nullable = true)
    private String descricao;


    @Column(nullable = true)
    private URL arquivo;


    @Column(nullable = true)
    private URL video;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
			name = "treino_aluno",
			joinColumns = @JoinColumn(name = "treino_id"),
			inverseJoinColumns = @JoinColumn(name = "aluno_id")		
			)
    private List<Usuario> alunos = new ArrayList<>();
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	public URL getArquivo() {
		return arquivo;
	}

	public void setArquivo(URL arquivo) {
		this.arquivo = arquivo;
	}

	public URL getVideo() {
		return video;
	}

	public void setVideo(URL video) {
		this.video = video;
	}
	
	public void addTreinos(Usuario usuarios) {
		this.alunos.add(usuarios);
		
	}

	public List<Usuario> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Usuario> alunos) {
		this.alunos = alunos;
	}
	
	
}

