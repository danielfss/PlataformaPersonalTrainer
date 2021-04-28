package com.compasso.academia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagamentoController {
	@GetMapping("/pagamento")
	public String viewHome() {
		return "/dashboard/pagamento";
	}
	
}
