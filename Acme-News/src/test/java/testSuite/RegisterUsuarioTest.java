package testSuite;



import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Usuario;
import services.BuscadorService;
import services.UsuarioService;
import utilities.AbstractTest;
import utilities.Md5;


@ContextConfiguration(locations = {
		"classpath:spring/junit.xml"
	})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegisterUsuarioTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;
	
	
	@Autowired
	private BuscadorService	buscadorService;
	
	// Test ------------------------------------------------------
	
	/*
	An actor who is not authenticated must be able to:
	Register to the system as a Usuario.

	01- All ok
	02- Blank username; Error
	03- Blank Password; Error
	05- Blank email; Error
	06- Used username; Error
	07- Used mail; Error 
	*/
	
	@Test
	public void driver() {
		final Object testingData[][] = {
			{null, "username1", "pass1", "emailTest1@example.com", "nombre1", "apellidos1"},
			{ConstraintViolationException.class, "", "pass2", "emailTest2@example.com", "nombre2", "apellidos2"},
			{DataIntegrityViolationException.class, "username3", "", "emailTest3@example.com", "nombre3", "apellidos3"},
			{DataIntegrityViolationException.class, "username4", "pass4", "", "nombre4", "apellidos4"},
			{DataIntegrityViolationException.class, "username1", "pass5", "emailTest5@example.com", "nombre5", "apellidos5"},
			{DataIntegrityViolationException.class, "username6", "pass6", "emailTest1@example.com", "nombre6", "apellidos6"},
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
			int i = this.usuarioService.findAll().size();
			
			authenticate(null);
			
			// Create Usuario
			Usuario usuario= this.usuarioService.create();
			// Setting Chorbi
			password = Md5.encodeMd5(pass);
			usuario.getUserAccount().setPassword(password);
			usuario.getUserAccount().setUsername(user);
			usuario.setEmail(email);
			usuario.setNombre(name);
			usuario.setApellidos(surname);
			usuario.setBuscador(this.buscadorService.create());

			// Save usuario
			this.usuarioService.save(usuario);
			
			Assert.isTrue(this.usuarioService.findAll().size() > i);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
