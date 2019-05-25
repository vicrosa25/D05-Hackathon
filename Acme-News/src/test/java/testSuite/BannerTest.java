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
	public void testCreateBanner(){
		Banner banner;
		Banner saved;
		
		super.authenticate("admin");	
		
		
		String url="http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";
		
		banner=bannerService.create();
		banner.setUrl(url);
		saved = bannerService.save(banner);
		Assert.isTrue(this.bannerService.findAll().contains(saved));
		
		super.unauthenticate();
		
	
	}
	
	//se intenta cambiar el banner autentificado como usuario. El resultado esperado es que no se pueda.
	@Test(expected = IllegalArgumentException.class)
	public void testChangeBannerNotLogguedAsAdmin(){
		Banner banner;
		Banner saved;
		
		super.authenticate("usuario4");	
		
		
		String url="http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";
		
		banner=bannerService.create();
		banner.setUrl(url);
		saved = bannerService.save(banner);
		Assert.isTrue(this.bannerService.findAll().contains(saved));
		
		super.unauthenticate();
	
	}
	
	//se intenta cambiar el banner sin autentificarse. El resultado esperado es que no se pueda.
	@Test(expected = IllegalArgumentException.class)
	public void testChangeBannerNotLoggued(){
		Banner banner;
		Banner saved;
		
		super.authenticate(null);	
		
		
		String url="http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";
		
		banner=bannerService.create();
		banner.setUrl(url);
		saved = bannerService.save(banner);
		Assert.isTrue(this.bannerService.findAll().contains(saved));
		
		super.unauthenticate();
	
	}
	
	

	
}
