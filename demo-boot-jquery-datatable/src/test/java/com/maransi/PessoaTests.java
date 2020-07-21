package com.maransi;



import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.maransi.service.PessoaService;

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
	
/*	
	public Optional<Pessoa> findByCpf( String cpf ) {
		return pessoaRepository.findByCpf(cpf);
	}

	public Optional<Pessoa> findById( Long id ){
		return pessoaRepository.findById(id);
	}

*/
}
