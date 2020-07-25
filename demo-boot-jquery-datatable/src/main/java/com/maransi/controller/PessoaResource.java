package com.maransi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@RequestMapping(// value="/nome",
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=*/*")
//	@GetMapping(produces = "application/json")	
	public @ResponseBody List<Pessoa> findPessoa(Pessoa pessoa) {
		List<Pessoa> pessoas = null;

		if (!pessoa.getNome().isEmpty()) {
			pessoas = pessoaService.findByNome(pessoa.getNome());
		} else if (!pessoa.getCpf().isEmpty()) {
			pessoas = new ArrayList<Pessoa>();

			Optional<Pessoa> p = pessoaService.findByCpf(pessoa.getCpf());

			if (p.isPresent()) {
				pessoas.add(p.get());
			}
		}

		System.out.println(pessoas.toString());

		return pessoas;
	}

}
