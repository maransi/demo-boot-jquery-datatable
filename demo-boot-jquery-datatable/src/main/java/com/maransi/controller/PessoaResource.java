package com.maransi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.maransi.model.Pessoa;
import com.maransi.service.PessoaService;

@RestController
@RequestMapping("pessoa/api")
public class PessoaResource {

	@Autowired
	PessoaService pessoaService;
	
	@RequestMapping( method=RequestMethod.GET, 
					produces=MediaType.APPLICATION_JSON_VALUE ,
					headers = "Accept=*/*")
//	@GetMapping(produces = "application/json")	
	public @ResponseBody List<Pessoa> findByNome(  Pessoa pessoa ){
		List<Pessoa> pessoas = pessoaService.findByNome(pessoa.getNome());
		
		System.out.println(pessoas.toString());
		
		return pessoas;
	}
	
}
