
package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.UsuarioService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UsuarioCambiarStatusTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;

	/**
	1-->significa que el usuario asciende de estatus
	2-->significa que el usuario no tenia puntos suficientes para ascender
	3-->significa que el usuario ya tenia el estatus máximo
	
	Test:
		
		 1. Un usuario asciende de estatus 						--> 1
		 2. Asciende de estatus alguien que no es un usuario	--> error
		 3. Asciende de estatus alguien que no es un usuario	--> error
		 4. Asciende de estatus alguien que no es un usuario	--> error
		 5. Un usuario intenta ascender sin puntos suficientes	--> 2
		 6. Un usuario con rango maximo intenta ascender		--> 3

	**/
	
	@Test
	public void driverTestCambiarEstatus() {
		final Object testingData[][] = {
			{
				null, "usuario1", 1
			},	
			{
				IllegalArgumentException.class, null, 1
			}, 
			{
				IllegalArgumentException.class, "manager1", 1
			},
			{
				IllegalArgumentException.class, "admin", 1
			},
			{
				null, "usuario2", 2
			},
			{
				null, "usuario3", 3
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateTestCambiarEstatus((Class<?>) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateTestCambiarEstatus(final Class<?> expected, final String username, Integer resultadoEsperado) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(username);
			Assert.isTrue(this.usuarioService.cambiarEstatus() == resultadoEsperado);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
