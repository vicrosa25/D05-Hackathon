package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.Usuario;
import services.UsuarioService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UsuarioNoSeguirTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;



	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Unfollow a user"
	 *  
	 * 1. Positive test.
	 *  
	 **/
	//Se intenta dejar de seguir a alguien que no seguia. Resultado esperado False
	@Test
	public void testnoSeguirUsuarioIncorrecto() {
		this.authenticate("usuario2");
		Usuario principal = this.usuarioService.findOneByName("usuario2");
		Usuario usuario = this.usuarioService.findOneByName("usuario1");
		this.usuarioService.noSeguirUsuario(usuario);
		Assert.isTrue(!principal.getSiguiendo().contains(usuario));
	}




	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Unfollow a user"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void testnoSeguirUsuarioCorrecto() {
		this.authenticate("usuario2");
		Usuario usuario = this.usuarioService.findOneByName("usuario3");
		this.usuarioService.noSeguirUsuario(usuario);
	}
}
