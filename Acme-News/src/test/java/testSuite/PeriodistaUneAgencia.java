package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Agencia;
import services.AgenciaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PeriodistaUneAgencia extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AgenciaService 	agenciaService;

	
	/**
	 * Requirement: An actor who is authenticated as a hacker must be able to:  "Create an Application"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void jourlnalistSubscribePositive() {
		Agencia agencia;
		agencia = this.agenciaService.findOne(super.getEntityId("agencia2"));
		
		this.agenciaService.join(agencia.getId());
		
		super.authenticate("periodista1");
	}
	
	
	/**
	 * Requirement: An actor who is authenticated as a periodista must be able to:  "Join to an Agency"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The journalist is allready a member of the agency
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void jourlnalistSubscribeNegative() {
		Agencia agencia;
		agencia = this.agenciaService.findOne(super.getEntityId("agencia1"));
		
		this.agenciaService.join(agencia.getId());
		
		super.authenticate("periodista1");
	}
}
