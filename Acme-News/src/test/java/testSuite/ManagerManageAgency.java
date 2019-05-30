package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Agencia;
import services.AgenciaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManagerManageAgency extends AbstractTest {
	
	
	// System under test ------------------------------------------------------	
	@Autowired
	private AgenciaService		agenciaService;
	
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Create an Agency"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void managerCreateAgencyPositive() {
		int 		agenciesNumber;
		int 		finalAgenciesNumber;
		Agencia 	agency;
		
		agenciesNumber = this.agenciaService.findAll().size();
		
		super.authenticate("manager1");
		
		try {
			// Creating, setting and saving the agency
			agency = this.agenciaService.create();
			agency.setTitulo("titulo");
			agency.setDireccion("direccion");
			agency.setCapacidadDisponible(6);
			agency.setTasa(1.3);
			this.agenciaService.save(agency);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		finalAgenciesNumber = this.agenciaService.findAll().size();
		
		// Cheking for the new agency
		Assert.isTrue(agenciesNumber < finalAgenciesNumber);
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Create an Agency"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated as a manager
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void managerCreateAgencyNegative() {
		super.authenticate("periodista1");
		this.agenciaService.create();
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Edit his or hers Agencies"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void managerEditAgencyPositive() {
		Agencia agency = null;
		Agencia saved = null;
		super.authenticate("manager1");
		
		try {
			// Creating, setting and saving the agency
			agency = this.agenciaService.findOne(super.getEntityId("agencia1"));
			agency.setTitulo("Changed title");
			saved = this.agenciaService.save(agency);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Cheking for the new agency
		Assert.isTrue(agency.getId() == saved.getId());
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Create an Agency"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The manager does not own the agency to edit
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void managerEditAgencyNegative() {
		Agencia agency = null;
		super.authenticate("manager2");
		
		agency = this.agenciaService.findOne(super.getEntityId("agencia1"));
		agency.setTitulo("Changed title");
		this.agenciaService.save(agency);
		
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Delete his or hers Agencies"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void managerDeleteAgencyPositive() {
		int 		agenciesNumber;
		int 		finalAgenciesNumber;
		Agencia 	agency;
		
		super.authenticate("manager1");
		
		agenciesNumber = this.agenciaService.findAll().size();
		
		try {
			agency = this.agenciaService.findOne(super.getEntityId("agencia1"));
			this.agenciaService.delete(agency.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		finalAgenciesNumber = this.agenciaService.findAll().size();
		
		// Cheking for the agency is deleted
		Assert.isTrue(agenciesNumber >  finalAgenciesNumber);
		super.unauthenticate();
	}
	
	/**
	 * Requirement: An actor who is authenticated as a manager must be able to:  "Delete his or hers Agencies"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The manager does not own the agency to edit
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void managerDeleteAgencyNegative() {

		super.authenticate("manager2");
		
		this.agenciaService.delete(super.getEntityId("agencia1"));
		
		super.unauthenticate();
	}
}
