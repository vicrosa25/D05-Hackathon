package testSuite;

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Sorteo;
import services.SorteoService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminGestionaSorteoTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private SorteoService sorteoService;
	
	
	/**
	 * Requirement: An actor who is authenticated as an admin must be able to:  "Create a Rufle"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void adminCreateSorteoPositive() {
		int 		sorteosNumber;
		int 		finalSorteosNumber;
		Sorteo 		sorteo;
		
		sorteosNumber = this.sorteoService.findAll().size();
		
		super.authenticate("admin");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		try {
			// Creating, setting and saving the sorteo
			sorteo = this.sorteoService.create();
			sorteo.setTitulo("sorteo");
			sorteo.setDescripcion("descripcion");
			sorteo.setFechaInicio(dateFormat.parse("19/10/2019"));
			sorteo.setFechaVencimiento(dateFormat.parse("20/10/2019"));
	
			this.sorteoService.save(sorteo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		finalSorteosNumber = this.sorteoService.findAll().size();
		
		// Cheking for the new sorteo
		Assert.isTrue(sorteosNumber < finalSorteosNumber);
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as an admin must be able to:  "Create a Rufle"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void adminCreateSorteoNegative() {
		super.authenticate("periodista1");
		this.sorteoService.create();
		super.unauthenticate();
	}
	
	
	/**
	 * Requirement: An actor who is authenticated as an admin must be able to:  "Edit a Rufle"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void adminEditRufflePositive() {
		Sorteo sorteo = null;
		Sorteo saved = null;
		super.authenticate("admin");
		
		try {
			// Creating, setting and saving the agency
			sorteo = this.sorteoService.findOne(super.getEntityId("sorteo1"));
			sorteo.setTitulo("Changed title");
			saved = this.sorteoService.save(sorteo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Cheking for the new agency
		Assert.isTrue(sorteo.getId() == saved.getId());
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Edit a Rufle"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void adminEditRuffleNegative() {
		Sorteo sorteo = null;
		super.authenticate("manager2");
		
		sorteo = this.sorteoService.findOne(super.getEntityId("sorteo1"));
		sorteo.setTitulo("Changed title");
		this.sorteoService.save(sorteo);
		
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as an admin must be able to:  "Compute Winner"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void adminComputeWinnerPositive() {
		Sorteo sorteo;
		super.authenticate("admin");
		
		sorteo = this.sorteoService.findOne(super.getEntityId("sorteo2"));
		this.sorteoService.computeWinner(sorteo.getId());
		
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as an admin must be able to:  "Compute Winner"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void adminComputeWinnerNegative() {
		Sorteo sorteo;
		super.authenticate("peridista1");
		
		sorteo = this.sorteoService.findOne(super.getEntityId("sorteo2"));
		this.sorteoService.computeWinner(sorteo.getId());
		
		super.unauthenticate();
	}
}
