package com.maransi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maransi.model.Pessoa;
import com.maransi.repository.PessoaRepository;

@Service("pessoaService")
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository; 
	
	
	public List<Pessoa> findByNome(String nome){
		return pessoaRepository.findByNome("%" + nome + "%");
	}
	
	public Optional<Pessoa> findByCpf( String cpf ) {
		return pessoaRepository.findByCpf(cpf);
	}

	public Optional<Pessoa> findById( Long id ){
		return pessoaRepository.findById(id);
	}
	
}
