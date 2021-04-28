package com.compasso.academia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.compasso.academia.model.Role;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.RoleRepository;
import com.compasso.academia.service.AppService;

@Controller
public class HomeController {
	
	@Autowired
	private AppService service;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@GetMapping("/home")
	public String viewHome() {
		return "/home";
	}
	
	@GetMapping("/contato")
	public String viewContato() {
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
	public String viewCadastro(Model model ) {
		model.addAttribute("usuario", new Usuario());
		
		return "/cadastro";
	}
	
	@PostMapping("/submeter_registro")
	public String processaRegistro(@ModelAttribute Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(usuario.getSenha());
		
		Role roleUser =  roleRepo.findByName("ALUNO");

		usuario.addRole(roleUser);
		usuario.setSenha(encodedPassword);
		usuario.setEnabled(true);
		
		service.saveUsuario(usuario);
		
		return "redirect:/home";
	}
}
