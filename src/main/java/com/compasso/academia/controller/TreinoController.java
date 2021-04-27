package com.compasso.personaltrainer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compasso.personaltrainer.model.Treino;
import com.compasso.personaltrainer.model.Usuario;
import com.compasso.personaltrainer.repository.TreinoRepository;
import com.compasso.personaltrainer.repository.UsuarioRepository;
import com.compasso.personaltrainer.service.AppService;

@Controller
public class TreinoController {
	
	@Autowired
	private TreinoRepository treinoRepo;
	
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private AppService service;
	
	@GetMapping("/treino_aluno")
	public String viewTreinoAluno() {
		return "/dashboard/treino_aluno";
	}
	
	@GetMapping("/editar_treino/{id}")
	public String viewTreino(@PathVariable ( value = "id") Long id, Model model){
		model.addAttribute("treino", new Treino());
		Usuario usuario = usuarioRepo.findPorId(id);
		
		/* model.addAttribute("treino", treino); */
		
		return "/personal/editar_treino";
		
	}
	
	@GetMapping("/personal/cadastrar_treino")
	public String mostraFormTreino(Model model){
		model.addAttribute("treino", new Treino());
		return "/personal/cadastrar_treino";
		
	}
	
	@RequestMapping(value = "/submeter_treino", method = RequestMethod.POST)
	public String processaTreino(@ModelAttribute Treino treino) {
				
		 treinoRepo.save(treino);
	
		return "redirect:/dashboard/lista_treinos";
	}
	
	
	@GetMapping("/lista_treinos")
	public String viewListaTreinos(Model model) {
		List<Treino> listaTreinos = service.getTreinos();
		model.addAttribute("listaTreinos", listaTreinos);
		
		return "/dashboard/lista_treinos";
	}
	
	
	@GetMapping("/detalhesTreino/{id}")
	public String viewDetalhesTreino(@PathVariable ("id") long id, Model model) {
		Treino treino = treinoRepo.findById(id);
		model.addAttribute("treino", treino);
		
		
		return "/dashboard/detalhesTreino";
	}
	
	@PostMapping("/detalhesTreino/{id}")
	public String detalhesTreinoSave(@PathVariable ("id") long id, Usuario usuario, Model model) {
		List<Usuario> listaUsuarios = service.getUsuarios();
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		Treino treino = treinoRepo.findById(id);
		
		usuario.addTreinos(treino);

		usuarioRepo.save(usuario);
		
		return "redirect/dashboard/detalhesTreino/{id}";
	}

}
