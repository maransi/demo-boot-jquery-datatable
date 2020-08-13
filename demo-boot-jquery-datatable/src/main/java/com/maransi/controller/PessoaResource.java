package com.maransi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

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

	
// Testar ( Retornar o @RquestBody; No construtor da classe Pessoa adcionar a annotation @JsonProperty	
	
	@RequestMapping(// value="/nome",
			method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, 
					headers = "Accept=*/*")
//	@GetMapping(produces = "application/json")	
//  No postman colocar os parametros como "form-data"
	public @ResponseBody Map<String, List<Pessoa>> findPessoa( Pessoa pessoa) {
		List<Pessoa> pessoas = null;

		if ( pessoa.getNome() != null && pessoa.getNome().trim() != "") {
			pessoas = pessoaService.findByNome(pessoa.getNome());
		} else if ( pessoa.getCpf() != null && pessoa.getCpf().trim() != "" ) {
			pessoas = new ArrayList<Pessoa>();

			Optional<Pessoa> p = pessoaService.findByCpf(pessoa.getCpf());

			if (p.isPresent()) {
				pessoas.add(p.get());
			}
		} else if ( pessoa.getSequencial() != null && pessoa.getSequencial() > 0 ) {
			pessoas = new ArrayList<Pessoa>();

			Optional<Pessoa> p = pessoaService.findById(pessoa.getSequencial());

			if (p.isPresent()) {
				pessoas.add(p.get());
			}
		}

		System.out.println(pessoas.toString());

		Map<String, List<Pessoa>> mapa = new TreeMap<String, List<Pessoa>>();
		
		mapa.put("data", pessoas);
		
		return mapa;
	}

}
