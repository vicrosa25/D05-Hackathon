
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Banner;
import services.BannerService;

@Controller
@RequestMapping("/banner")
public class BannerController extends AbstractController {

	@Autowired
	private BannerService bannerService;


	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Banner> banners;

		banners = this.bannerService.findAll();

		result = new ModelAndView("banner/admin/list");
		result.addObject("banners", banners);
		result.addObject("requestURI", "banner/admin/list.do");
		return result;
	}

	// Create GET --------------------------------------------------
	@RequestMapping(value = "admin/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Banner banner;

		banner = this.bannerService.create();
		result = this.createEditModelAndView(banner);

		return result;
	}

	// Create POST ------------------------------------------------------------------------
	@RequestMapping(value = "admin/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Banner banner, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().toString());
			result = this.createEditModelAndView(banner);
		} else {
			try {
				this.bannerService.save(banner);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(banner, "administrador.commit.errorBanner");
			}
		}
		return result;
	}

	// Delete ------------------------------------------------------
	@RequestMapping(value = "admin/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int bannerId) {
		ModelAndView result;
		Banner banner;

		banner = this.bannerService.findOne(bannerId);

		try {
			this.bannerService.delete(banner);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(banner, "administrador.commit.errorBanner");
		}

		return result;
	}

	// Ancillary Methods-------------------------------------------------------------------------
	protected ModelAndView createEditModelAndView(Banner banner) {
		ModelAndView result;
		result = this.createEditModelAndView(banner, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Banner banner, String message) {
		ModelAndView result;

		result = new ModelAndView("banner/admin/create");
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}

}
