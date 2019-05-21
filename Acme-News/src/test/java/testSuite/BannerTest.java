package testSuite;



import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


import domain.Banner;

import services.BannerService;

import utilities.AbstractTest;


@ContextConfiguration(locations = {"classpath:spring/junit.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BannerTest extends AbstractTest {
	
	// System under test ------------------------------------------------------
	@Autowired
	private BannerService bannerService;

	//Se cambia el banner autentificado como administrador.
	@Test
	public void testChangeBanner(){
		this.authenticate("admin");	
		String urlNueva="http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";
		Banner banner=bannerService.getBanner();
		banner.getImagenes().add(urlNueva);
		bannerService.save(banner);
		Assert.isTrue(banner.getImagenes().contains(urlNueva));
		
	
	}
	
	//se intenta cambiar el banner autentificado como usuario. El resultado esperado es que no se pueda.
	@Test(expected = IllegalArgumentException.class)
	public void testChangeBannerNotLogguedAsAdmin(){
		this.authenticate("usuario4");	
		String urlNueva="http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";
		Banner banner=bannerService.getBanner();
		banner.getImagenes().add(urlNueva);
		bannerService.save(banner);
		Assert.isTrue(banner.getImagenes().contains(urlNueva));
	
	}
	
	//se intenta cambiar el banner sin autentificarse. El resultado esperado es que no se pueda.
	@Test(expected = IllegalArgumentException.class)
	public void testChangeBannerNotLoggued(){
		this.authenticate(null);	
		String urlNueva="http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";
		Banner banner=bannerService.getBanner();
		banner.getImagenes().add(urlNueva);
		bannerService.save(banner);
		Assert.isTrue(banner.getImagenes().contains(urlNueva));
	
	}
	
	

	
}
