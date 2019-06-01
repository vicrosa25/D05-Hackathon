package testSuite;



import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Agencia;
import domain.Evento;
import services.AgenciaService;
import services.EventoService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ManagerManageEventoTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private EventoService 	eventoService;
	
	
	@Autowired
	private AgenciaService	agenciaService;


	/**
	 * Requirement: An actor who is authenticated as an manager must be able to:  "Create an Event "
	 *
	 *  1- Creando evento todo bien		-> OK
	 *	2- Crear evento como periosidta	-> Error
	 * 
	 */
	@Test
	public void driverTestCreateEvento() {
		final Object testingData[][] = {
			{null, "manager1","titulo"},
			{IllegalArgumentException.class, "periodista1","titulo"},
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateEvento((Class<?>) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2]);

	}


	/**
	 * Requirement: An actor who is authenticated as an manager must be able to:  "Create an Event "
	 *
	 *	1- Borrando evento todo bien				-> OK
	 *	2- Borrando evento de la que no es autor	-> Error
	 *	3- Borrando evento siendo usuario			-> Error
	 * 
	 */
	@Test
	public void driverTestDeleteEvento() {
		final Object testingData[][] = {
			{null, "manager1", "evento1"},
			{IllegalArgumentException.class, "manager1","evento3"},
			{IllegalArgumentException.class, "usuario1", "evento1"}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteEvento((Class<?>) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateCreateEvento(Class<?> expected,String username, String titulo) {
		Class<?> caught;
		caught = null;
		Evento evento;
		Agencia agencia;
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		
		
		try {
			super.authenticate(username);

			evento = this.eventoService.create();
			agencia = this.agenciaService.findOne(super.getEntityId("agencia1"));
			
			evento.setTitulo(titulo);
			evento.setDescripcion("descripcion");
			evento.setImagen("https://pbs.twimg.com/profile_images/524580321254060033/wtYWns2U_400x400.png");
			evento.setFecha(dateFormat.parse("18/07/2019"));
			evento.setDireccion("Direccion");
			evento.setAgencia(agencia);

			this.eventoService.save(evento);

			super.unauthenticate();
			
		} catch (Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}


	protected void templateDeleteEvento(Class<?> expected, String username, String eventoName) {
		Class<?> caught;
		caught = null;
		Evento evento;
		
		try {
			this.authenticate(username);

			evento = this.eventoService.findOne(super.getEntityId(eventoName));
			this.eventoService.delete(evento);

			this.unauthenticate();
			
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}