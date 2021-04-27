package com.compasso.academia.model;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    
    @OneToMany
    private List<Usuario> usuarios;
    
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

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}

