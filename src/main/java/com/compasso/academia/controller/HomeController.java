package com.compasso.academia.controller;

import com.compasso.academia.dto.UsuarioDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.RoleRepository;
import com.compasso.academia.repository.UsuarioRepository;
import com.compasso.academia.service.AppService;

import javax.validation.Valid;

@Controller
public class HomeController {
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	private AppService service;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@GetMapping("/home")
	public String viewHome() {
		return "/home";
	}
	
	@GetMapping("/contato")
	public String viewContato(Model model) {
		Optional<Usuario> usuario = usuarioRepo.findById(1l);
		String url = "https://api.whatsapp.com/send?phone="+usuario.get().getTelefone();
		model.addAttribute("url", url);
		return "/contato";
	}
	
	@GetMapping("/sobre")
	public String viewSobreNos() {
		return "/sobre_nos";
	}
	
	@GetMapping("/login")
	public String viewLoginPage() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth == null || auth instanceof AnonymousAuthenticationToken) {
				return "login";
			}
		} catch (BadCredentialsException ex) {
			System.out.println("Dados errados");
			return "/403";
		} catch (LockedException ex) {
			System.out.println("Conta Bloqueada");
			return "/403";
		} catch (DisabledException ex) {
			System.out.println("Conta Desabilitada");
			return "/403";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/cadastro")
	public String viewCadastro(@ModelAttribute UsuarioDTO usuario, Model model) {
		model.addAttribute("usuarioDTO", usuario);
		model.addAttribute("usuario", new Usuario());
		
		return "/cadastro";
	}
	
	@PostMapping("/cadastro")
		public String salvar(@Valid UsuarioDTO usuario, BindingResult bindingResult) {

		if(usuario.getSenha() != null && usuario.getCsenha() != null){
			if(!usuario.getCsenha().equals(usuario.getSenha())){
				bindingResult.addError(new FieldError("usuario", "csenha",
						"A confirmacao de senha deve ser igual a senha!"));
			}
		}

		if(bindingResult.hasErrors()){
			return "cadastro";
		}

		usuario.createUser();
		return "redirect:/login";
	}
}
