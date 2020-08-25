package com.maransi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.maransi.model.Pessoa;

@Repository("pessoaRepository")
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	Optional<Pessoa> findByCpf(String cpf);
	
	@Query("Select p From Pessoa p Where nome like :nome")
	List<Pessoa> findByNome( String nome );
}
