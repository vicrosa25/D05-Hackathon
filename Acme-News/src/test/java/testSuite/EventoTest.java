package testSuite;



import java.util.Date;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.EventoService;
import utilities.AbstractTest;
import domain.Evento;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EventoTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private EventoService eventoService;



	/*
	1- Creando evento todo bien	-> OK
	2- Crear evento como moderador	-> Error
	3- Crear evento sin titulo		-> Error
	 */
	@Test
	public void driverTestCreateEvento() {
		final Object testingData[][] = {
			{null, "periodista1","titulo"},
			{IllegalArgumentException.class, "moderador1","titulo"},
			{ConstraintViolationException.class, "periodista1",""}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateEvento((Class<?>) testingData[i][0],
				(String) testingData[i][1],
				(String) testingData[i][2]);

	}


	/*
	1- Borrando evento todo bien				-> OK
	2- Borrando evento de la que no es autor	-> Error
	3- Borrar evento con id erroneo				-> Error
	4- Borrando evento siendo usuario			-> Error
	 */

	@Test
	public void driverTestDeleteEvento() {
		final Object testingData[][] = {
			{null, "periodista1",114},
			{IllegalArgumentException.class, "periodista2",115},
			{IllegalArgumentException.class, "periodista1",1232334134},
			{IllegalArgumentException.class, "usuario1",116}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteEvento((Class<?>) testingData[i][0],
				(String) testingData[i][1],
				(Integer) testingData[i][2]);

	}

	// Ancillary methods ------------------------------------------------------
	@SuppressWarnings("deprecation")
	protected void templateCreateEvento(final Class<?> expected,final String username,
		final String titulo) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);

			Evento evento = this.eventoService.create();

			evento.setDescripcion("descripcion");
			evento.setImagen("https://pbs.twimg.com/profile_images/524580321254060033/wtYWns2U_400x400.png");
			Date fecha = new Date();
			fecha.setYear(2018);
			evento.setFecha(fecha);
			evento.setDireccion("Direccion");

			evento.setTitulo(titulo);

			int numEventos = this.eventoService.findAll().size();

			this.eventoService.saveNew(evento);

			Assert.isTrue(this.eventoService.findAll().size()>numEventos);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}


	protected void templateDeleteEvento(final Class<?> expected,final String username,
		final Integer eventoId) {
		Class<?> caught;
		caught = null;
		try {
			this.authenticate(username);

			Evento evento = this.eventoService.findOne(eventoId);

			int numEventos = this.eventoService.findAll().size();

			this.eventoService.delete(evento);

			Assert.isTrue(this.eventoService.findAll().size()<numEventos);

			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}