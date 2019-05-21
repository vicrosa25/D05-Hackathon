package testSuite;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import services.AgenciaService;
import utilities.AbstractTest;
import domain.Agencia;
import domain.Manager;
import domain.Periodista;

@ContextConfiguration(locations = { "classpath:spring/junit.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AgenciaTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private AgenciaService agenciaService;
	@Autowired
	private ActorRepository actorRepository;

	// Suscribirse de una agencia. Si el periodista está suscrito se espera que
	// de error.
	@Test
	public void testSuscribirseSuscrito() {
		this.authenticate("periodista1");
		Class<?> caught = null;
		String loggedUsername = LoginService.getPrincipal().getUsername();
		Periodista logged = (Periodista) actorRepository
				.findOneByName(loggedUsername);
		try {
			if (logged.getAgencia() != null) {
				throw new IllegalArgumentException();
			} else {
				this.agenciaService.join(111);
			}
		} catch (Exception e) {
			caught = e.getClass();
		}
		super.checkExceptions(IllegalArgumentException.class, caught);
	}

	@Test
	public void testSuscribirseNoSuscrito() {
		this.authenticate("periodista5");
		String loggedUsername = LoginService.getPrincipal().getUsername();
		Periodista logged = (Periodista) actorRepository
				.findOneByName(loggedUsername);
		if (logged.getAgencia() != null) {
			throw new IllegalArgumentException();
		} else {
			this.agenciaService.join(111);
		}
		Assert.isTrue(logged.getAgencia().getId() == 111);
	}

	// Save agencia
	@Test
	public void testSaveAgencia() {
		this.authenticate("manager1");
		String loggedUsername = LoginService.getPrincipal().getUsername();
		Manager logged = (Manager) actorRepository
				.findOneByName(loggedUsername);
		Agencia nueva = new Agencia();
		nueva.setCapacidadDisponible(10);
		nueva.setDireccion("dir");
		nueva.setManager(logged);
		nueva.setTitulo("Titulo");
		nueva.setTasa(10.0);

		Integer before = agenciaService.findAll().size();
		agenciaService.save(nueva);
		Assert.isTrue(agenciaService.findAll().size() > before);
	}
}
