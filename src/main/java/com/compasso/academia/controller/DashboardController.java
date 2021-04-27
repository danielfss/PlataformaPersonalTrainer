package com.compasso.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.compasso.academia.dto.AulaDto;
import com.compasso.academia.model.Aula;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.AulaRepository;
import com.compasso.academia.service.AppService;

@Controller
public class DashboardController {
	
	@Autowired
	private AppService service;
	
	@GetMapping("/personal/dashboard_personal")
	public String viewDashboardPersonal() {
		return "/personal/dashboard_personal";
	}
	
	
	@GetMapping("/dashboard_aluno")
	public String viewDashboardAluno() {
		return "/dashboard/dashboard_aluno";
	}
	
//	@GetMapping("/personal/agenda_personal")
//	public String viewAgendaPersonal() {
//		return "/personal/agenda_personal";
//	}
	
	/* CONTROLLER AULA */
	@Autowired
	private AulaRepository aulaRepository;
	
    @GetMapping("/personal/agenda_personal")
	public String viewAgendaPersonal(AulaDto aulaDto) {		
    	return "/personal/agenda_personal";
	}

    @PostMapping("inserirAula")
    public String inserirAula(@Valid AulaDto aulaDto, BindingResult result) {
		
		if(result.hasErrors()) {
			return "/personal/agenda_personal";
		}
		
		Aula aula = aulaDto.toAula();
		aulaRepository.save(aula);
	
		return "redirect:/personal/dashboard_personal";
	}
    /* FIM CONTROLLER AULA */
	
	@GetMapping("/agenda_aluno")
	public String viewAgendaAluno() {
		return "/dashboard/agenda_aluno";
	}
	
	@GetMapping("/personal/lista_alunos")
	public String viewListaUsuario(Model model) {
		List<Usuario> listaUsuarios = service.getUsuarios();
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		return "/personal/lista_alunos";
	}
}
