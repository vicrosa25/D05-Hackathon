
package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import domain.Banner;


@Controller
@RequestMapping("/administrador")
public class AdministradorController extends AbstractController {




	@Autowired
	private BannerService bannerService;




	public AdministradorController() {
		super();
	}

	@RequestMapping(value = "/changeBanner", method = RequestMethod.GET)
	public ModelAndView changeBanner() {
		ModelAndView result;

		Banner banner=this.bannerService.getBanner();


		result = this.createEditModelAndView(banner);
		return result;

	}

	@RequestMapping(value = "/changeBanner", method = RequestMethod.POST, params = "save")
	public ModelAndView saveBanner(@Valid Banner banner, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(banner, "administrador.commit.errorBanner");


		} else {
			try {
				this.bannerService.isUrl(banner.getImagenes());
				this.bannerService.save(banner);


				result = new ModelAndView("redirect:../");

			} catch (Throwable oops) {
				result = this.createEditModelAndView(banner, "administrador.commit.errorBanner");
			}

		}

		return result;
	}



	// Ancillary Methods--------------------------
	protected ModelAndView createEditModelAndView(Banner banner) {
		ModelAndView result;
		result = this.createEditModelAndView(banner, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Banner banner, String message) {
		ModelAndView result;

		result = new ModelAndView("administrador/changeBanner");
		result.addObject("bannerEdit", banner);
		result.addObject("message", message);

		return result;
	}


}
