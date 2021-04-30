package com.compasso.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.compasso.academia.model.Identificador;
import com.compasso.academia.model.Treino;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.TreinoRepository;
import com.compasso.academia.repository.UsuarioRepository;
import com.compasso.academia.security.UsuarioDetails;
import com.compasso.academia.service.AppService;

@Controller
public class TreinoController {
	
	@Autowired
	private TreinoRepository treinoRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private AppService service;
	
	@GetMapping("/dashboard/treino_aluno/{id}")
	public String viewTreinoAluno(@PathVariable ("id") long id, Model model) {
		
		
		Treino treino = treinoRepo.findById(id);
		
		model.addAttribute("treinoAluno", treino);
		
		return "/dashboard/treino_aluno";
	}
	
	@GetMapping("/editar_treino/{id}")
	public String viewEditarTreino(@PathVariable (value = "id") Long id, Model model){
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
	
	@GetMapping("/dashboard/lista_treinos")
	public String viewListaTreinos(Model model) {
		List<Treino> listaTreinos = service.getTreinos();
		model.addAttribute("listaTreinos", listaTreinos);
		
		return "/dashboard/lista_treinos";
	}
	
	
	  @GetMapping("/dashboard/meus_treinos") 
	  public String viewListaMeusTreinos(Model model, @AuthenticationPrincipal UsuarioDetails loggedUser) {
	  
	  List<Treino> todosTreinos = loggedUser.getTreinos();
	  
	  
	  //List<Aula> agendados = new ArrayList<>();
	  
	  model.addAttribute("meusTreinos", todosTreinos);
	  
	  return "/dashboard/meus_treinos"; 
	  
	  }
	  
	  
	 
	
	@RequestMapping(value="/dashboard/detalhesTreino/{id}", method=RequestMethod.GET)
	public String viewDetalhesTreino(@PathVariable ("id") long id, Model modelTreino, Model modelAluno, Model modelTodosAlunos, Model modelPegaIdAluno) {
		
		List<Usuario> todosUsuarios = usuarioRepo.findAll();
		Treino treino = treinoRepo.findById(id);

		
		modelTreino.addAttribute("treino", treino);
		modelTodosAlunos.addAttribute("todosUsuarios", todosUsuarios);
		modelPegaIdAluno.addAttribute("identificador", new Identificador());
		
		
		return "/dashboard/detalhesTreino";
	}
	
	@RequestMapping(value="/dashboard/detalhesTreino/{idd}", method=RequestMethod.POST)
	public String addAlunoAoTreino(@PathVariable ("idd") long idd, @ModelAttribute Identificador ident) {
		
		Treino treino = treinoRepo.findById(idd);
		
		Usuario usuario = usuarioRepo.findPorId(ident.getId());		
			
		usuario.addTreinos(treino);
		
		service.saveUsuario(usuario);

		return "redirect:/dashboard/detalhesTreino/{idd}";
	}
	

		

}
