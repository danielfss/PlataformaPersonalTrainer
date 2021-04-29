package com.compasso.academia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AgendaController {

	@Autowired
	private AppService service;
	/* CONTROLLER AULA */
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@RequestMapping(value = "/dashboard/agenda_aluno", method=RequestMethod.GET)
	public String viewAgendaAluno(Model model, Model modelPegaIdHorario) {
		List<Aula> aula = aulaRepository.findAll();
		
		List<Aula> disponivel = new ArrayList<>();		
		
		 for(Aula tp : aula){    
			   if(tp.getAluno() == null) {
				   disponivel.add(tp);				   
			   }
			 }
		model.addAttribute("disponivel", disponivel);
		
		modelPegaIdHorario.addAttribute("identificador", new Identificador());
		
		return "/dashboard/agenda_aluno";
	}
	
	@RequestMapping(value = "/dashboard/agenda_aluno", method=RequestMethod.POST)
	public String AgendaAluno(@AuthenticationPrincipal UsuarioDetails loggedUser, @ModelAttribute Identificador ident, Model model){
		
		model.addAttribute("identificador", ident);
		
		String email = loggedUser.getUsername();
		Usuario usuario = usuarioRepo.findByEmail(email);
		
		Aula agendada = aulaRepository.findById(ident.getId());
		agendada.setAluno(usuario);
		
		
		aulaRepository.save(agendada);
		
		return "redirec:/dashboard/agenda_aluno";
	}
	
	@GetMapping("/personal/agenda_personal")
	public String viewAgendaPersonal(AulaDto aulaDto, Model model, Model modelAgendados) {
		List<Aula> aulas = service.getAulas();
		
		List<Aula> agendados = new ArrayList<>();
		for(Aula tp : aulas){    
			   if(tp.getAluno() != null) {
				   agendados.add(tp);
			   }
			 }
	
		model.addAttribute("aulas", aulas);
		
		modelAgendados.addAttribute("agendados", agendados);
		
		return "/personal/agenda_personal";
	}

	@RequestMapping(value = "/deletaHorario/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deletaHorario(@PathVariable (value ="id") Long id) {
		
		service.deleteAgendado(id);
		
		return "redirect:/personal/agenda_personal";
		
	}
	
	@RequestMapping(value = "/deletaAgendado/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String deletaAgendado(@PathVariable (value ="id") Long id) {
		
		service.deleteAgendado(id);
		
		return "redirect:/personal/agenda_personal";
		
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

	
}
