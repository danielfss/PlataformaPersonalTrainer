package com.compasso.academia.controller;

import com.compasso.academia.model.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @RequestMapping("dashboard")
    @ResponseBody
    public String dashboard(@AuthenticationPrincipal Usuario user){
        // TODO: Implementar a verificacao do cargo do usuario
        // If User: If Admin...
        return "Ola " + user.getNome() +
                "\nEmail: " + user.getEmail() +
                "Security: " + user.getAuthorities();
    }
}
