package testSuite;

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
public class ModeratorBanUbanUserTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private ModeradorService moderadorService;
	
	
	@Autowired
	private UsuarioService 	 usuarioService;
	
	
	// Test ------------------------------------------------------
	/*
	 * An actor who is not authenticated as a moderator must be able to: Ban an User
	 * 
	 * 01- All ok 													- Positive test
	 * 02- Actor is not autheticate 								- Negative test - error
	 * 03- Actor is authenticated as a journalist					- Negative test - error
	 * 
	 */

	
	@Test
	public void driverTestBanChorbi() {
		final Object testingData[][] = {
			{"moderador1", null}, 
			{null, IllegalArgumentException.class},
			{"periodista", IllegalArgumentException.class}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((String) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(final String username, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		Usuario user;
		
		// Get user1
		user = this.usuarioService.findOne(super.getEntityId("usuario1"));
		
		try {
			
			super.authenticate(username);
			
			// Ban the user
			this.moderadorService.saveBanUnban(user.getId());
			
			// Uban the user
			this.moderadorService.saveBanUnban(user.getId());
			
			super.unauthenticate();
			
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
