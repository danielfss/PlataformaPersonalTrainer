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
import com.compasso.academia.repository.AulaRepository;
import com.compasso.academia.service.AppService;

@Controller
public class AulaController {

	@Autowired
	private AppService service;
	/* CONTROLLER AULA */
	@Autowired
	private AulaRepository aulaRepository;

	@GetMapping("/dashboard/agenda_aluno")
	public String viewDashboardAluno(Model model) {
		List<Aula> aulas = service.getAulas();
		model.addAttribute("aulas", aulas);
		return "/dashboard/agenda_aluno";
	}

	@GetMapping("/personal/agenda_personal")
	public String viewAgendaPersonal(AulaDto aulaDto, Model model) {
		List<Aula> aulas = service.getAulas();
		model.addAttribute("aulas", aulas);
		return "/personal/agenda_personal";
	}

	/* Endpoint do professor */
	@PostMapping("inserirAula")
	public String inserirAula(@Valid AulaDto aulaDto, BindingResult result) {

		if (result.hasErrors()) {
			return "/personal/agenda_personal";
		}

		Aula aula = aulaDto.toAula();
		aulaRepository.save(aula);

		return "redirect:/personal/agenda_personal";
	}

	/* Endpoint do aluno */
	@PostMapping("agendaAula")
	public String agendarAula() {
		return "/dashboard/agenda_aluno";
	}
	/* FIM CONTROLLER AULA */
}
