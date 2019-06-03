
package testSuite;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.AdminService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminDashBoardTest extends AbstractTest {

	// System under test ---------------------------------------------------------------------------
	@Autowired
	private AdminService adminService;


	// Tests Query1-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 1: El mínimo, media y máximo número de noticias publicadas por un periodista
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query1Positive() {
		super.authenticate("admin");
		this.adminService.query1();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 1: El mínimo, media y máximo número de noticias publicadas por un periodista
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query1Negative() {
		super.authenticate(null);
		this.adminService.query1();
		super.unauthenticate();
	}

	// Tests Query2-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 2: El nombre del periodista que más dinero ha obtenido con sus noticias.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query2Positive() {
		super.authenticate("admin");
		this.adminService.query2();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 2: El nombre del periodista que más dinero ha obtenido con sus noticias.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query2Negative() {
		super.authenticate(null);
		this.adminService.query2();
		super.unauthenticate();
	}

	// Tests Query3-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 3: El número de usuarios con estatus principiante
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query3Positive() {
		super.authenticate("admin");
		this.adminService.query3_1();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 3: El número de usuarios con estatus principiante
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query3Negative() {
		super.authenticate(null);
		this.adminService.query3_1();
		super.unauthenticate();
	}


	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 3: El número de usuarios con estatus veterano
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query3_2Positive() {
		super.authenticate("admin");
		this.adminService.query3_2();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 3: El número de usuarios con estatus veterano
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query3_2Negative() {
		super.authenticate(null);
		this.adminService.query3_2();
		super.unauthenticate();
	}
	
	
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 3: El número de usuarios con estatus maestro
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query3_3Positive() {
		super.authenticate("admin");
		this.adminService.query3_3();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 3: El número de usuarios con estatus maestro
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query3_3Negative() {
		super.authenticate(null);
		this.adminService.query3_3();
		super.unauthenticate();
	}

	// Tests Query4-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 4: El mínimo, media y máximo de puntos necesario para apuntarse a un sorteo.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query4Positive() {
		super.authenticate("admin");
		this.adminService.query4();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 4: El mínimo, media y máximo de puntos necesario para apuntarse a un sorteo.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query4Negative() {
		super.authenticate(null);
		this.adminService.query4();
		super.unauthenticate();
	}

	// Tests Query5-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 5: El número de total de puntos que se han conseguido entre todos los usuarios.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query5Positive() {
		super.authenticate("admin");
		this.adminService.query5();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 5: El número de total de puntos que se han conseguido entre todos los usuarios.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query5Negative() {
		super.authenticate(null);
		this.adminService.query5();
		super.unauthenticate();
	}

	// Tests Query6-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 6: Lista de usuarios en orden de descendiente según el número de puntos.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query6Positive() {
		super.authenticate("admin");
		this.adminService.query6();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 6: Lista de usuarios en orden de descendiente según el número de puntos.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query6Negative() {
		super.authenticate(null);
		this.adminService.query6();
		super.unauthenticate();
	}


	// Tests Query7-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 7: Lista de periodistas en orden de descendiente según el número total de visitas de sus noticias.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query7Positive() {
		super.authenticate("admin");
		this.adminService.query7();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 7: Lista de periodistas en orden de descendiente según el número total de visitas de sus noticias.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query7Negative() {
		super.authenticate(null);
		this.adminService.query7();
		super.unauthenticate();
	}

	// Tests Query8-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 8: Número de noticias baneadas.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query8Positive() {
		super.authenticate("admin");
		this.adminService.query8();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 8: Número de noticias baneadas.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query8Negative() {
		super.authenticate(null);
		this.adminService.query8();
		super.unauthenticate();
	}

	// Tests Query9-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 9: Número de noticias publicadas.
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query9Positive() {
		super.authenticate("admin");
		this.adminService.query9();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 9: Número de noticias publicadas.
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query9Negative() {
		super.authenticate(null);
		this.adminService.query9();
		super.unauthenticate();
	}

	// Tests Query10-----------------------------------------------------------------------------------------
	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 10: El número de sorteos que hay en el sistema. (futuros, activos y pasados)
	 * 
	 * 2. Positive test.
	 * 
	 **/
	@Test
	public void query10Positive() {
		super.authenticate("admin");
		this.adminService.query10();
		super.unauthenticate();
	}

	/**
	 * Requirement: An actor who is authenticated as an administrator must be able to Display a dashboard.
	 * 
	 * 1. Query 10: El número de sorteos que hay en el sistema. (futuros, activos y pasados)
	 * 
	 * 2. Negative test.
	 * 3. Business rule that is intended to broke: The actor is not authenticated as an Admin
	 * 
	 **/
	@Test(expected = IllegalArgumentException.class)
	public void query10Negative() {
		super.authenticate(null);
		this.adminService.query10();
		super.unauthenticate();
	}
}
