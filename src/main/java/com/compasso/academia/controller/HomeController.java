package com.compasso.academia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/home/sobre")
    public String sobre(){
        return "sobre_nos";
    }
}
