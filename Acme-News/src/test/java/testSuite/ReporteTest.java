package testSuite;



import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import services.NoticiaService;
import services.ReporteService;
import utilities.AbstractTest;
import domain.Noticia;
import domain.Reporte;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ReporteTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private ReporteService reporteService;



	@Autowired
	private NoticiaService noticiaService;



	//1- Crear reporte como usuario, resultado esperado ok
	//2- Crear reporte como admin, resultado esperado fail
	//3- Crear reporte sin autentificarse, resultado esperado fail
	//4- Crear reporte como usuario a una noticia inventada, resultado esperado fail
	//5- Crear reporte como usuario, sin pasarle la noticia, resultado esperado fail

	@Test
	public void driverTestCreateReporte() {
		final Object testingData[][] = {
			{null, "usuario4","noticia9"},
			{IllegalArgumentException.class, "admin","noticia9"},
			{IllegalArgumentException.class, null,"noticia9"},
			{AssertionError.class,  "usuario4","noticia20"},
			{AssertionError.class,  "usuario4",null}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateReporte((Class<?>) testingData[i][0],
				(String) testingData[i][1],
				(String) testingData[i][2]);

	}

	// Ancillary methods ------------------------------------------------------
	protected void templateCreateReporte(final Class<?> expected,final String username,final String noticiaTitulo) {
		Class<?> caught;
		caught = null;
		try {

			this.authenticate(username);
			Integer antes= this.reporteService.findAll().size();
			Noticia noticia=this.noticiaService.findOneByName(noticiaTitulo);
			Reporte reporte= this.reporteService.create(noticia);
			reporte.setTexto("Prueba testCreateReporteAuthenticateAsUsuario");
			Reporte reporte2=this.reporteService.save(reporte);

			Integer despues=this.reporteService.findAll().size();
			Assert.isTrue(this.reporteService.findAll().contains(reporte2));
			Assert.isTrue(despues==antes+1);
			this.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.checkExceptions(expected, caught);
	}




}
