package com.maransi;



import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.maransi.model.Pessoa;
import com.maransi.service.PessoaService;
import com.maransi.util.UF;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes=DemoBootJqueryDatatableApplication.class)
@Transactional
class PessoaTests {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PessoaService pessoaService;

	@Test
	@Order(2)
	void testFindByName() throws Exception{
		logger.info("\n Testing testFindByName()");
		
		
		try {
			assertNotNull(pessoaService.findByNome("%MARCO ANTONIO DA SILVA%") ); // %NELSON DOUGLAS MARCOS OLIVEIRA%"));
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() );
		}
		
		
	}
	
	@Test
	@Order(3)
	void findByCpf() throws Exception{
		
		try {
			assertNotNull( pessoaService.findByCpf("106.523.608-58"));
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception(e.getMessage());
		}
		
		
	}
	
	@Test
	@Order(4)
	void testFindById() throws Exception{
		
		try {
			assertNotNull( pessoaService.findById(1L) );
		} catch (Exception e) {
			e.printStackTrace();
			
			throw new Exception( e.getMessage() );
		}
	}

	@Test
	@Order(5)
	@Rollback(true)
	void testCrud() throws Exception{
		
		try {
			Pessoa pessoa = pessoaService.findById(1L).orElse(null);
			
			pessoa.setNome("ELISANGELA APARECIDA DE ALMEIDA Update");
			
			pessoaService.update(pessoa);
			
			pessoa = pessoaService.findById(1L).orElse(null);
			
			assertEquals("ELISANGELA APARECIDA DE ALMEIDA Update", pessoa.getNome() );
			
			logger.info("\n pessoaService.update {}", pessoa);
			
			pessoa = new Pessoa("037.440.710-06","JOSE DA SILVA", LocalDate.of(2001,1,1),"RUA DA BELEZAS 30","SÃO PAULO","00000000","11 9 65833040",new BigDecimal("10000"),UF.SP); 			
			
			pessoaService.insert(pessoa);
						
			pessoa = pessoaService.findByCpf("037.440.710-06").orElse(null);
			
			logger.info("\n pessoaService.insert {}", pessoa);

			assertEquals("JOSE DA SILVA", pessoa.getNome());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
/*	
	public Optional<Pessoa> findByCpf( String cpf ) {
		return pessoaRepository.findByCpf(cpf);
	}

	public Optional<Pessoa> findById( Long id ){
		return pessoaRepository.findById(id);
	}

*/
}
