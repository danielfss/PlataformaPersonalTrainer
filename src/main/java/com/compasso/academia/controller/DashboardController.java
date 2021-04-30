package com.compasso.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.compasso.academia.model.Treino;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.security.UsuarioDetails;
import com.compasso.academia.service.AppService;

@Controller
public class DashboardController {

	@Autowired
	private AppService service;

	@GetMapping("/personal/dashboard_personal")
	public String viewDashboardPersonal() {
		return "/personal/dashboard_personal";
	}

	@GetMapping("/dashboard/dashboard_aluno")
	public String viewDashbardAluno(Model model, @AuthenticationPrincipal UsuarioDetails loggedUser) {
		  
		  List<Treino> todosTreinos = loggedUser.getTreinos();		
		  
		  model.addAttribute("meusTreinos", todosTreinos);
		  
		  return "/dashboard/dashboard_aluno"; 
		  
		  }

	@GetMapping("/personal/lista_alunos")
	public String viewListaUsuario(Model model) {
		List<Usuario> listaUsuarios = service.getUsuarios();
		model.addAttribute("listaUsuarios", listaUsuarios);

		return "/personal/lista_alunos";
	}
}
