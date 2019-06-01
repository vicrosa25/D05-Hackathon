
package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Informacion;
import services.InformacionService;
import services.UsuarioService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UsuarioCompartirInformacionTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private InformacionService informacionService;


	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Share Information"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void testcompartirInformacionCorrecto() {
		this.authenticate("usuario2");
		Informacion informacion = this.informacionService.getInformacionRepository().findOneByName("tituloEvento1");
		Assert.isTrue(this.usuarioService.compartirInformacion(informacion));
	}

	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Share Information"
	 *  
	 * 	1. Negative test.
	 * 	2. Business rule that is intended to broke: Information already shared
	 *  
	 **/
	@Test
	public void testcompartirInformacionIncorrecto() {
		this.authenticate("usuario2");
		Informacion informacion = this.informacionService.getInformacionRepository().findOneByName("tituloEvento4");
		Assert.isTrue(!this.usuarioService.compartirInformacion(informacion));
	}
}
