package testSuite;



import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Informacion;
import domain.Sorteo;
import services.InformacionService;
import services.SorteoService;
import services.UsuarioService;
import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UsuarioTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;


	@Autowired
	private InformacionService informacionService;

	@Autowired
	private SorteoService sorteoService;

	//se intenta compartir informacion que no compartias
	@Test
	public void testcompartirInformacionCorrecto(){
		this.authenticate("usuario2");
		Informacion informacion = this.informacionService.getInformacionRepository().findOneByName("tituloEvento1");
		Assert.isTrue(this.usuarioService.compartirInformacion(informacion));
	}

	//se intenta compartir informacion que ya compartias previamente
	@Test
	public void testcompartirInformacionIncorrecto(){
		this.authenticate("usuario2");
		Informacion informacion= this.informacionService.getInformacionRepository().findOneByName("tituloEvento4");
		Assert.isTrue(!this.usuarioService.compartirInformacion(informacion));
	}



	//2-->operación realizada con éxito
	//1-->significa que el usuario no tiene suficientes puntos para apuntarse al sorteo
	//0-->significa que el usuario ya contenia el sortea y no deberia poder apuntarse
	@Test
	public void driverTestApuntarseSorteo() {
		final Object testingData[][] = {
			{null, "usuario1","Sorteo3",2},	//un usuario se registra a un sorteo en el que no estaba --> 2
			{IllegalArgumentException.class, null,"Sorteo3",2}, //se apunta un sorteo alguien que no es un usuario-->error
			{IllegalArgumentException.class, "manager1","Sorteo3",2},//se apunta un sorteo alguien que no es un usuario-->error
			{IllegalArgumentException.class, "admin","Sorteo3",2},//se apunta un sorteo alguien que no es un usuario-->error
			{null, "usuario1","Sorteo2",0},//un usuario intenta apuntarse a un sorteo en el que ya estaba-->1
			{null, "usuario5","Sorteo2",1}//un usuario sin puntos se intenta registrar a un sorteo
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



	//1-->significa que el usuario asciende de estatus
	//2-->significa que el usuario no tenia puntos suficientes para ascender
	//3-->significa que el usuario ya tenia el estatus máximo
	@Test
	public void driverTestCambiarEstatus() {
		final Object testingData[][] = {
			{null, "usuario1",1},	//un usuario asciende de estatus --> 1
			{IllegalArgumentException.class, null,1}, //asciende de estatus alguien que no es un usuario-->error
			{IllegalArgumentException.class, "manager1",1},//asciende de estatus alguien que no es un usuario-->error
			{IllegalArgumentException.class, "admin",1},//asciende de estatus alguien que no es un usuario-->error
			{null, "usuario2",2},//un usuario intenta ascender sin puntos suficientes-->2
			{null, "usuario3",3}//un usuario con rango maximo intenta ascender
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateTestCambiarEstatus((Class<?>) testingData[i][0],
				(String) testingData[i][1],
				(Integer) testingData[i][2]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateTestCambiarEstatus(final Class<?> expected,final String username, Integer resultadoEsperado) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(username);
			Assert.isTrue(this.usuarioService.cambiarEstatus()==resultadoEsperado);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}


}
