package com.compasso.academia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compasso.academia.dto.AulaDto;
import com.compasso.academia.model.Aula;
import com.compasso.academia.model.Identificador;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.AulaRepository;
import com.compasso.academia.repository.UsuarioRepository;
import com.compasso.academia.security.UsuarioDetails;
import com.compasso.academia.service.AppService;


@Controller
public class AulaController {

	@Autowired
	private AppService service;
	/* CONTROLLER AULA */
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@RequestMapping(value = "/dashboard/agenda_aluno", method=RequestMethod.GET)
	public String viewDashboardAluno(Model model, Model modelPegaIdHorario ) {
		List<Aula> aulas = aulaRepository.findAll();
		
		model.addAttribute("aulas", aulas);
		
		modelPegaIdHorario.addAttribute("identificador", new Identificador());
		
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
	@RequestMapping(value = "/dashboard/agenda_aluno", method=RequestMethod.POST)
	public String AgendaAluno(@AuthenticationPrincipal UsuarioDetails loggedUser, @ModelAttribute Identificador ident, Model model){
		
		model.addAttribute("identificador", ident);
		
		String email = loggedUser.getUsername();
		Usuario usuario = usuarioRepo.findByEmail(email);
		
		Aula agendada = aulaRepository.findById(ident.getId());
		agendada.setAluno(usuario);
		
		
		aulaRepository.save(agendada);
		
		return "redirect:/dashboard/agenda_aluno";
	}
	/* FIM CONTROLLER AULA */
}
