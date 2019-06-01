package testSuite;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Moderador;
import services.ModeradorService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ModeradorRetiraDineroTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private ModeradorService moderadorService;
	

	/**
	 * Requirement: An actor who is authenticated as an moderador must be able to:  "Retirar Dinero"
	 * 
	 * 	1. Positive test.
	 *  2. Negative test. Business rule that is intended to broke: She or he has to own more than 5 euros
	 * 
	 */
	@Test
	public void driverTestBanChorbi() {
		final Object testingData[][] = {
			{0, null}, 
			{1, IllegalArgumentException.class}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(int i,final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			List<Moderador> moderadores=new ArrayList<Moderador>();
			moderadores.addAll(this.moderadorService.findAll());
			moderadores.get(0).getCartera().setSaldoAcumulado(500);
			moderadores.get(1).getCartera().setSaldoAcumulado(2);
			super.authenticate(moderadores.get(i).getUserAccount().getUsername());
			this.moderadorService.retirarDinero();
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
