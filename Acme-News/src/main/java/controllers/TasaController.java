package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.TasaService;
import controllers.AbstractController;
import domain.Tasa;

@Controller
@RequestMapping("/tasa/administrator")
public class TasaController extends AbstractController {
	
	// Services ---------------------------------------------------------------

	@Autowired
	private TasaService tasaService;


	// Constructors -----------------------------------------------------------

	public TasaController() {
		super();
	}
	
	// Edit -------------------------------------------------------------------
	
	@RequestMapping(value = "changeTasa", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result = createEditModelAndView(tasaService.find());

		return result;
	}
	
	// Save -------------------------------------------------------------------
	
	@RequestMapping(value = "changeTasa", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Tasa tasa, BindingResult binding) {
		ModelAndView result;

		tasa = tasaService.reconstruct(tasa, binding);
		if (binding.hasErrors()) {
			result = createEditModelAndView(tasa);
		} else {
			try {
				tasaService.save(tasa);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(tasa, "tasa.commit.error");
			}
		}

		return result;
	}
	

	
	// Ancillary methods ------------------------------------------------------
	
	//** Model and view ********************************************************//

	private ModelAndView createEditModelAndView(Tasa tasa) {
		ModelAndView result;

		result = createEditModelAndView(tasa, null);

		return result;
	}

	private ModelAndView createEditModelAndView(Tasa tasa, String message) {
		ModelAndView result;

		result = new ModelAndView("tasa/edit");
		result.addObject("tasa", tasa);
		result.addObject("message", message);

		return result;
	}

}
