package testSuite;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.Premio;
import services.PremioService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BorrarPremioTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private PremioService premioService;
	
//Borrar Premio, test positivo y negativo.
	
	@Test
	public void driverTestBanChorbi() {
		final Object testingData[][] = {
			//Borrar Premio No usado en ningún concurso, se espera correcto, id del premio = 152.
			{152, null}, 
			//Borrar Premio que es usado en un concurso, se espera excepcion, id del premio = 151.
			{151, IllegalArgumentException.class}
		};

		for (int i = 0; i < testingData.length; i++)
			this.template((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}

	// Ancillary methods ------------------------------------------------------
	protected void template(int id, final Class<?> expected) {
		Class<?> caught;
		caught = null;
		try {
			List<Premio> premios=new ArrayList<Premio>();
			premios.addAll(this.premioService.getPremioRepository().findAll());
			System.out.println(premios);
			for(Premio p:premios){
				System.out.println(p.getId());
				if(p.getId()==id){
					System.out.println("hola");
					this.premioService.delete(p);
				}

			}
			
			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
