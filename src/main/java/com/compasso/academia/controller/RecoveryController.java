package com.compasso.academia.controller;

import com.compasso.academia.dto.RecoveryDTO;
import com.compasso.academia.dto.UsuarioDTO;
import com.compasso.academia.model.Usuario;
import com.compasso.academia.repository.RoleRepository;
import com.compasso.academia.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RecoveryController {
    private static final Logger logger = LoggerFactory.getLogger(RecoveryController.class);
    @Autowired
    private UsuarioRepository userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/recovery")
    public String resetPassword(@ModelAttribute RecoveryDTO usuario, Model model){

        model.addAttribute("usuario", usuario);

        return "/recovery";
    }

    @PostMapping("/recovery")
    public String sendPassword(@Valid RecoveryDTO usuario, BindingResult bindingResult){
        Usuario verify = userRepo.findByEmail(usuario.getEmail());


        if(usuario.getSenha() != null && usuario.getCsenha() != null){
            if(!usuario.getCsenha().equals(usuario.getSenha())){
                bindingResult.addError(new FieldError("usuario", "csenha",
                        "A confirmacao de senha deve ser igual a senha!"));
            }
        }


        if(userRepo.findByEmail(usuario.getEmail()) != null){
            if(verify.getEmail().equals(usuario.getEmail())){
                if(verify.getToken().equals(usuario.getToken())){
                    verify.setSenha(encoder.encode(usuario.getSenha()));
                    userRepo.save(verify);
                }
            }

        }

        if(bindingResult.hasErrors()){
            return "cadastro";
        }

        return "redirect:/login";
    }
}
