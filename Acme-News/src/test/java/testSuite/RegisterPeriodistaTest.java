package testSuite;



import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import datatypes.Cartera;
import domain.Periodista;
import services.PeriodistaService;
import utilities.AbstractTest;
import utilities.Md5;


@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegisterPeriodistaTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private PeriodistaService 	periodistaService;
	
	
	// Test ------------------------------------------------------
	
	/**
	An actor who is not authenticated must be able to: Register to the system as a Periodista.

			01- All well  				ok
			02- Blank username;			Error
			03- Blank Password; 		Error
			05- Blank email; 			Error
			06- Used username; 			Error
			07- Used mail; 				Error 
	
	**/
	
	@Test
	public void driver() {
		final Object testingData[][] = {
			{null, "username1", "pass1", "emailTest1@example.com", "nombre1", "apellidos1"},
			{ConstraintViolationException.class, "", "pass2", "emailTest2@example.com", "nombre2", "apellidos2"},
			{ConstraintViolationException.class, "username3", "", "emailTest3@example.com", "nombre3", "apellidos3"},
			{ConstraintViolationException.class, "username4", "pass4", "", "nombre4", "apellidos4"},
			{ConstraintViolationException.class, "username1", "pass5", "emailTest5@example.com", "nombre5", "apellidos5"},
			{ConstraintViolationException.class, "username6", "pass6", "emailTest1@example.com", "nombre6", "apellidos6"},
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((Class<?>) testingData[i][0], (String) testingData[i][1],(String) 
					testingData[i][2],(String) testingData[i][3],(String) testingData[i][4],(String) testingData[i][5]);
	}
	
	// Ancillary methods ------------------------------------------------------
	protected void template(final Class<?> expected,final String user,final String pass,
			final String email,final String name, final String surname) {
		Class<?> caught;
		caught = null;
		try {
			String password;
			int i = this.periodistaService.findAll().size();
			
			authenticate(null);
			
			// Create Periodista
			Periodista periodista= this.periodistaService.create();
			
			// Setting Periodista
			password = Md5.encodeMd5(pass);
			periodista.getUserAccount().setPassword(password);
			periodista.getUserAccount().setUsername(user);
			periodista.setEmail(email);
			periodista.setNombre(name);
			periodista.setApellidos(surname);
			periodista.setCartera(new Cartera());
			periodista.getCartera().setPaypalEmail("prueba@paypal.com");
			periodista.getCartera().setSaldoAcumulado(0.0);
			periodista.getCartera().setSaldoAcumuladoTotal(0.0);
			
			// Save periodista
			this.periodistaService.save(periodista);
			
			Assert.isTrue(this.periodistaService.findAll().size() > i);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
