package testSuite;



import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.ComentarioService;
import services.InformacionService;
import utilities.AbstractTest;
import domain.Comentario;
import domain.Informacion;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ComentarioTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ComentarioService comentarioService;



	@Autowired
	private InformacionService informacionService;



	//1- Crear comentario como usuario a una noticia, resultado esperado ok
	//2- Crear comentario como admin, resultado esperado fail
	//3- Crear comentario sin autentificarse, resultado esperado fail
	//4- Crear comentario como usuario a una informacion inventada, resultado esperado fail
	//5- Crear comentario como usuario, sin pasarle la informacion, resultado esperado fail
	//6- Crear comentario como usuario, sin titulo, resultado esperado fail
	//7- Crear comentario como usuario, sin descripcion, resultado esperado fail
	//8- Crear comentario como usuario a un evento,  resultado esperado ok

	@Test
	public void driverTestCreateComentario() {
		final Object testingData[][] = {
			{null, "usuario4","noticia9","titulo","descripcion"},
			{IllegalArgumentException.class, "admin","noticia9","titulo","descripcion"},
			{IllegalArgumentException.class, null,"informacion9","titulo","descripcion"},

			{AssertionError.class,  "usuario4","noticia9",null,"descripcion"},
			{AssertionError.class,  "usuario4","noticia9","titulo",null},
			{null,  "usuario4","tituloEvento3","titulo","descripcion"},
			{DataIntegrityViolationException.class,  "usuario4","informacion20","titulo","descripcion"},
			{DataIntegrityViolationException.class,  "usuario4",null,"titulo","descripcion"},

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateComentario((Class<?>) testingData[i][0],
				(String) testingData[i][1],
				(String) testingData[i][2],
				(String) testingData[i][3],
				(String) testingData[i][4]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateCreateComentario(final Class<?> expected,final String username,
		final String informacionTitulo,final String tituloComentario,final String descripcionComentario) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(username);
			Integer antes= this.comentarioService.findAll().size();
			Informacion informacion=this.informacionService.getInformacionRepository().findOneByName(informacionTitulo);
			Comentario comentario= this.comentarioService.create(informacion);
			comentario.setTitulo(tituloComentario);
			comentario.setDescripcion(descripcionComentario);
			Comentario comentario2=this.comentarioService.save(comentario);

			Integer despues=this.comentarioService.findAll().size();
			Assert.isTrue(this.comentarioService.findAll().contains(comentario2));
			Assert.isTrue(despues==antes+1);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}




}
