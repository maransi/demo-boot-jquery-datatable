package com.maransi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PessoaController {

	@RequestMapping("/cadastro")
	public String cadastro() {
		
		return "pessoa/pessoa";
	}
	
}
