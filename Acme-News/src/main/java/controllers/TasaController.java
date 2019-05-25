package controllers;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.TasaService;
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


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Edit -------------------------------------------------------------------

	@RequestMapping(value = "changeTasa", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result = this.createEditModelAndView(this.tasaService.find());

		return result;
	}

	// Save -------------------------------------------------------------------

	@RequestMapping(value = "changeTasa", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Tasa tasa, BindingResult binding) {
		ModelAndView result;

		tasa = this.tasaService.reconstruct(tasa, binding);
		if (binding.hasErrors()) {
			result = this.createEditModelAndView(tasa);
		} else {
			try {
				this.tasaService.save(tasa);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(tasa, "tasa.commit.error");
			}
		}

		return result;
	}



	// Ancillary methods ------------------------------------------------------

	//** Model and view ********************************************************//

	private ModelAndView createEditModelAndView(Tasa tasa) {
		ModelAndView result;

		result = this.createEditModelAndView(tasa, null);

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
