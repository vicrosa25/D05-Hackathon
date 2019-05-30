
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

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class AdminChangePrincipalAddTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private BannerService bannerService;


	// Test ------------------------------------------------------
	/*
	 * An actor who is not authenticated as a admin must be able to: change the principal page adds
	 * 
	 * 01- All ok - Positive test
	 * 02- Actor is not autheticate - Negative test - error
	 * 03- Actor is authenticated as a user - Negative test - error
	 * 
	 */

	@Test
	public void testCreateBanner() {
		Banner banner;
		Banner saved;

		super.authenticate("admin");

		String url = "http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";

		banner = bannerService.create();
		banner.setUrl(url);
		saved = bannerService.save(banner);
		Assert.isTrue(this.bannerService.findAll().contains(saved));

		super.unauthenticate();

	}


	@Test(expected = IllegalArgumentException.class)
	public void testChangeBannerNotLoggued() {
		Banner banner;
		Banner saved;

		super.authenticate(null);

		String url = "http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";

		banner = bannerService.create();
		banner.setUrl(url);
		saved = bannerService.save(banner);
		Assert.isTrue(this.bannerService.findAll().contains(saved));

		super.unauthenticate();

	}


	@Test(expected = IllegalArgumentException.class)
	public void testChangeBannerNotLogguedAsAdmin() {
		Banner banner;
		Banner saved;

		super.authenticate("usuario4");

		String url = "http://www.marketingsgm.es/wp-content/uploads/anuncio-ford.jpg";

		banner = bannerService.create();
		banner.setUrl(url);
		saved = bannerService.save(banner);
		Assert.isTrue(this.bannerService.findAll().contains(saved));

		super.unauthenticate();

	}

}
