
package testSuite;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Categoria;
import domain.Noticia;
import services.NoticiaService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PeriodistaGestionaNoticiaTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private NoticiaService noticiaService;


	/**
	 * 
	 * Requirement: An actor who is authenticated as a journalis must be able to:  "Create a New"
	 * 
	 * 1- Creando noticia todo bien 			-> OK
	 * 2- Crear noticia como moderador 			-> Error
	 * 3- Crear noticia sin titulo 				-> Error
	 * 
	 **/
	@Test
	public void driverTestCreateNoticia() {
		final Object testingData[][] = {
			{
				null, "periodista1", "titulo"
			}, {
				IllegalArgumentException.class, "moderador1", "titulo"
			}, {
				ConstraintViolationException.class, "periodista1", ""
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateNoticia((Class<?>) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateCreateNoticia(final Class<?> expected, final String username, final String titulo) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);

			Noticia noticia = this.noticiaService.create();

			noticia.setCategoria(Categoria.CULTURA);
			noticia.setDescripcion("descripcion");
			noticia.setImagen("https://pbs.twimg.com/profile_images/524580321254060033/wtYWns2U_400x400.png");
			noticia.setVideo("https://www.youtube.com/watch?v=6ddO3jPUFpg");
			noticia.setTitulo(titulo);

			int numNoticias = this.noticiaService.findAll().size();
			this.noticiaService.saveNew(noticia);

			Assert.isTrue(this.noticiaService.findAll().size() > numNoticias);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}

	/**
	 * 
	 * Requirement: An actor who is authenticated as a journalis must be able to:  "Delte a New"
	 * 
	 * 1- Borrando noticia todo bien 					-> OK
	 * 2- Borrando noticia de la que no es autor 		-> Error
	 * 3- Borrar noticia con id erroneo 				-> Error
	 * 4- Borrando noticia siendo usuario 				-> Error
	 * 
	 **/

	@Test
	public void driverTestDeleteNoticia() {
		final Object testingData[][] = {
			{null, "periodista1", "noticia1"}, 
			{IllegalArgumentException.class, "periodista2", "noticia1"}, 
			{AssertionError.class, "periodista1", "noticia100"}, 
			{IllegalArgumentException.class, "usuario1", "noticia2"}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteNoticia((Class<?>) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2]);
	}

	protected void templateDeleteNoticia(final Class<?> expected, final String username, final String noticiaName) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);

			Noticia noticia = this.noticiaService.findOne(super.getEntityId(noticiaName));

			int numNoticias = this.noticiaService.findAll().size();

			this.noticiaService.delete(noticia);

			Assert.isTrue(this.noticiaService.findAll().size() < numNoticias);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}