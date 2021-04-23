package com.compasso.academia.controller;

import com.compasso.academia.model.Usuario;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping("dashboard")
    public String dashboard(@AuthenticationPrincipal Usuario user){
        // TODO: Implementar a verificacao do cargo do usuario
        // If User: If Admin...
        return "";
    }
}
