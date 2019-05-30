
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Premio;
import services.PremioService;

@Controller
@RequestMapping("/premio")
public class PremioController extends AbstractController {

	//Services-----------------------------------------------------------------------------------------------
	@Autowired
	private PremioService premioService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping("admin/list")
	public ModelAndView listAdmin() {
		ModelAndView result;
		Collection<Premio> premios;

		premios = premioService.findAll();
		result = new ModelAndView("premio/admin/list");
		result.addObject("premios", premios);

		return result;
	}

	// Display ---------------------------------------------------------------
	@RequestMapping("admin/display")
	public ModelAndView display(@RequestParam int premioId) {
		ModelAndView result;

		result = new ModelAndView("premio/admin/display");
		Premio premio = premioService.findOne(premioId);

		result.addObject("premio", premio);

		return result;
	}

	// Create & Edit Event -----------------------------------------------------------------------------------		
	@RequestMapping(value = "admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Premio premio;

		premio = premioService.create();
		result = createEditModelAndView2(premio);

		return result;
	}

	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int premioId) {
		ModelAndView result;
		Premio premio;

		premio = premioService.findOne(premioId);
		result = createEditModelAndView2(premio);

		return result;
	}

	// save event ---------------------------------------------------------------		
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Premio premio, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView2(premio);
		} else {
			try {
				premioService.save(premio);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView2(premio, "premio.commit.error");
			}
		}

		return result;
	}
	
	
	// delete event ---------------------------------------------------------------
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Premio premio, BindingResult bindingResult) {
		ModelAndView result;

		try {
			premioService.delete(premio);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView2(premio, "premio.commit.errorPremio");
		}

		return result;
	}

	// Other methods ---------------------------------------------------------------
	protected ModelAndView createEditModelAndView2(Premio premio) {
		assert premio != null;

		ModelAndView result;

		result = createEditModelAndView2(premio, null);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Premio premio, String message) {
		assert premio != null;

		ModelAndView result;

		result = new ModelAndView("premio/admin/edit");
		result.addObject("premio", premio);
		result.addObject("message", message);

		return result;
	}

}
