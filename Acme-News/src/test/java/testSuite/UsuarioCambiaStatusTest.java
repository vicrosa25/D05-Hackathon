package testSuite;



import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Sorteo;
import services.SorteoService;
import services.UsuarioService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UsuarioCambiaStatusTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private SorteoService sorteoService;

	

	/**
		2-->operación realizada con éxito
		1-->significa que el usuario no tiene suficientes puntos para apuntarse al sorteo
		0-->significa que el usuario ya contenia el sortea y no deberia poder apuntarse
	
		test:
			1. Un usuario se registra a un sorteo en el que no estaba 			--> 2
			2. Se apunta un sorteo alguien que no es un usuario					--> error
			3. Se apunta un sorteo alguien que no es un usuario					--> error
			4. Se apunta un sorteo alguien que no es un usuario					--> error
			5. Un usuario intenta apuntarse a un sorteo en el que ya estaba		--> 0
			6. Un usuario sin puntos se intenta registrar a un sorteo			--> 1 
	
	**/
	@Test
	public void driverTestApuntarseSorteo() {
		final Object testingData[][] = {
			{null, "usuario1","Sorteo3",2},	
			{IllegalArgumentException.class, null,"Sorteo3",2}, 
			{IllegalArgumentException.class, "manager1","Sorteo3",2},
			{IllegalArgumentException.class, "admin","Sorteo3",2},
			{null, "usuario1","Sorteo2",0},
			{null, "usuario5","Sorteo2",1}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateTestApuntarseSorteo((Class<?>) testingData[i][0],
				(String) testingData[i][1],
				(String) testingData[i][2],
				(Integer) testingData[i][3]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateTestApuntarseSorteo(final Class<?> expected,final String username,final String titulo, Integer resultadoEsperado) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(username);
			Sorteo sorteo= this.sorteoService.findOneByName(titulo);
			Assert.isTrue(this.usuarioService.apuntarseSorteo(sorteo)==resultadoEsperado);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}
}
