
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
public class UsuarioSeguirTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private UsuarioService usuarioService;


	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Follow to a user"
	 *  
	 * 	1. Positive test.
	 *  
	 **/
	@Test
	public void testseguirUsuarioCorrecto() {
		this.authenticate("usuario1");
		Usuario principal = this.usuarioService.findOneByName("usuario1");
		Usuario usuario = this.usuarioService.findOneByName("usuario2");
		this.usuarioService.seguirUsuario(usuario);
		
		Assert.isTrue(principal.getSiguiendo().contains(usuario));
	}

	
	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Follow to a user"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is authenticated as an manager
	 *  
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void testseguirUsuarioSinSerUsuario() {
		this.authenticate("manager");
		Usuario usuario = this.usuarioService.findOneByName("usuario1");
		this.usuarioService.seguirUsuario(usuario);

	}
	
	
	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Follow to a user"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The actor is not authenticated
	 *  
	 **/
	//Se intenta seguir a un usuario sin autentificarse. Resultado esperado es que no se pueda.
	@Test(expected = IllegalArgumentException.class)
	public void testseguirUsuarioSinAutentificarse() {

		this.authenticate(null);
		Usuario usuario = this.usuarioService.findOneByName("usuario1");
		this.usuarioService.seguirUsuario(usuario);

	}
	

	/**
	 * Requirement: An actor who is authenticated as an user must be able to:  "Follow to a user"
	 *  
	 * 1. Negative test.
	 * 2. Business rule that is intended to broke: The user is trying to follow a allready followed user
	 *  
	 **/
	//Un usuario intenta seguir a un usuario que ya seguia de antes, el resultado esperado es que no se pueda.
	@Test(expected = IllegalArgumentException.class)
	public void testseguirUsuarioVolverASeguirAlMismo() {
		this.authenticate("usuario2");
		Usuario usuario = this.usuarioService.findOneByName("usuario1");
		this.usuarioService.seguirUsuario(usuario);
	}
}
