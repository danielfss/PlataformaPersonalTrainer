package com.compasso.academia.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compasso.academia.model.AlunoStatus;
import com.compasso.academia.model.Role;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.UsuarioRepository;
import com.compasso.academia.security.UsuarioDetails;
import com.compasso.academia.service.AppService;

@Controller
public class PerfilController {
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private AppService service;
	
	@GetMapping("/meu_perfil")
	public String viewPerfil(@AuthenticationPrincipal UsuarioDetails loggedUser, Model model){
		
		String email = loggedUser.getUsername();
		Usuario usuario = usuarioRepo.findByEmail(email);
		model.addAttribute("usuario", usuario);
		
		return "/dashboard/minha_conta";	
	}
	
	@PostMapping("/save_perfil")
	public String updatePerfil(Usuario usuario, RedirectAttributes redirectAttributes, @AuthenticationPrincipal UsuarioDetails loggedUser) {
		usuario.setEnabled(true);
		usuario.setSenha(loggedUser.getPassword());
		usuario.setRoles(loggedUser.getRoles());
		usuario.setStatus(loggedUser.getStatus());
		
	
		
		usuarioRepo.save(usuario);
		
		redirectAttributes.addFlashAttribute("message", "Seu perfil foi atualizado!");
		
		return "redirect:/meu_perfil";
	}
	
	@RequestMapping(value="/usuario/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String trocaStatusConta(@PathVariable Long id) {
		
		Usuario aluno = usuarioRepo.findPorId(id);
		
		if(aluno.isEnabled() == true) {
				aluno.setEnabled(false);
				aluno.setStatus(AlunoStatus.DESATIVADA);
		}else {
			aluno.setEnabled(true);
			aluno.setStatus(AlunoStatus.ATIVADA);
		}
		
		service.saveUsuario(aluno);
		
		return "redirect:/personal/lista_alunos";
	}
	
	
	@GetMapping("/alterar_senha")
	public String viewAlteraSenha(@AuthenticationPrincipal UsuarioDetails loggedUser, Model model){
		
		String email = loggedUser.getUsername();
		Usuario usuario = usuarioRepo.findByEmail(email);
		model.addAttribute("usuario", usuario);
		
		return "/dashboard/alterar_senha";	
	}
	
	@PostMapping("/save_senha")
	public String updateSenha(Usuario usuario, @AuthenticationPrincipal UsuarioDetails loggedUser,RedirectAttributes redirectAttributes) {
		usuario.setEnabled(true);
		usuario.setRoles(loggedUser.getRoles());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(usuario.getSenha());
		usuario.setSenha(encodedPassword);
		usuario.setStatus(loggedUser.getStatus());
		
		service.saveUsuario(usuario);
		
		redirectAttributes.addFlashAttribute("message", "Senha Alterada com Sucesso!");
		
		return "redirect:/alterar_senha";
	}
	
	@PostMapping("/submeter_update")
	public String processaUpdate(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttributes) {
		Usuario user = usuarioRepo.findPorId(usuario.getId());
		usuario.setEnabled(true);
		usuario.setRoles(user.getRoles());
		usuario.setSenha(user.getSenha());
		usuario.setStatus(user.getStatus());
		usuarioRepo.save(usuario);
		
		redirectAttributes.addFlashAttribute("message", "Seu perfil foi atualizado!");
		
		return "redirect:/personal/lista_alunos";
	}
}
