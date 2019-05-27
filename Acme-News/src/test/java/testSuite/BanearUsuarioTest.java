package testSuite;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Usuario;
import services.ModeradorService;
import services.UsuarioService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BanearUsuarioTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private ModeradorService moderadorService;
	
	
	@Autowired
	private UsuarioService 	 usuarioService;
	

	
	@Test
	public void driverTestBanChorbi() {
		final Object testingData[][] = {
			//Banear usuario como moderador, se espera correcto.
			{"moderador1", null}, 
			//Banear usuario sin loguear, se espera excepcion.
			{null, IllegalArgumentException.class},
			//Banear usuario logueado como periodista, se espera excepcion.
			{"periodista", IllegalArgumentException.class}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			List<Usuario> users=new ArrayList<Usuario>();
			users.addAll(this.usuarioService.findAll());
			super.authenticate(username);
			this.moderadorService.saveBanUnban(users.get(0).getId());
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
