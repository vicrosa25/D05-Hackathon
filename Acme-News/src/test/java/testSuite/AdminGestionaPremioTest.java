package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Premio;
import services.PremioService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminGestionaPremioTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private PremioService premioService;
	
	
	/**
	 * Requirement: An actor who is authenticated as an admin must be able to:  "Create an Award"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void adminCreatePremioPositive() {
		int 		premiosNumber;
		int 		finalPremiosNumber;
		Premio 		premio;
		
		premiosNumber = this.premioService.findAll().size();
		
		super.authenticate("admin");
		
		try {
			// Creating, setting and saving the premio
			premio = this.premioService.create();
			premio.setNombre("nombre");
			premio.setPrecio(150);
			premio.setDescripcion("descripcion");
	
			this.premioService.save(premio);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		finalPremiosNumber = this.premioService.findAll().size();
		
		// Cheking for the new premio
		Assert.isTrue(premiosNumber < finalPremiosNumber);
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Create an Agency"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void managerCreateAgencyNegative() {
		super.authenticate("periodista1");
		this.premioService.create();
		super.unauthenticate();
	}
	
	
	
	
	// Test ------------------------------------------------------
	/*
	 * An actor who is not authenticated as a admin must be able to: Borrar a un premio si no está en sorteo
	 * 
	 * 01- All ok 						- Positive test
	 * 02- El premio está en un sorteo  - Negative test - error
	 * 
	 */
	@Test
	public void driverTestBanChorbi() {
		final Object testingData[][] = {
			//Borrar Premio No usado en ningún concurso, se espera correcto, id del premio = 152.
			{"premio3", null},
			//Borrar Premio que es usado en un concurso, se espera excepcion, id del premio = 151.
			{"premio1", IllegalArgumentException.class}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(String entityName, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Premio premio;
		
		try {
			super.authenticate("admin");
			premio = this.premioService.findOne(super.getEntityId(entityName));
			this.premioService.delete(premio);
			super.unauthenticate();
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		
		super.checkExceptions(expected, caught);
	}
}
