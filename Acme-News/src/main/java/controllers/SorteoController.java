
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
import domain.Sorteo;
import services.PremioService;
import services.SorteoService;

@Controller
@RequestMapping("/sorteo")
public class SorteoController extends AbstractController {


	//Services
	@Autowired
	private SorteoService	sorteoService;

	@Autowired
	private PremioService	premioService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping("admin/list")
	public ModelAndView listAdmin() {
		ModelAndView result;
		Collection<Sorteo> sorteos;

		sorteos = this.sorteoService.findAll();
		result = new ModelAndView("sorteo/admin/list");
		result.addObject("sorteos", sorteos);

		return result;
	}

	// Display ---------------------------------------------------------------
	@RequestMapping("admin/display")
	public ModelAndView display(@RequestParam int sorteoId) {
		ModelAndView result;

		result = new ModelAndView("sorteo/admin/display");
		Sorteo sorteo = this.sorteoService.findOne(sorteoId);
		result.addObject("sorteo", sorteo);
		result.addObject("premioId", sorteo.getPremio().getId());
		result.addObject("usuarios", sorteo.getUsuarios());
		return result;
	}

	// Create & Edit  ---------------------------------------------------------------
	@RequestMapping(value = "admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sorteo sorteo;

		sorteo = this.sorteoService.create();
		result = this.createEditModelAndView(sorteo);

		return result;
	}

	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sorteoId) {
		ModelAndView result;
		Sorteo sorteo;

		sorteo = this.sorteoService.findOne(sorteoId);
		result = this.createEditModelAndView(sorteo);
		return result;
	}

	// save  ---------------------------------------------------------------
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Sorteo sorteo, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().toString());
			result = this.createEditModelAndView(sorteo);
		} else {
			try {
				this.sorteoService.save(sorteo);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView(sorteo, "sorteo.commit.error");
			}
		}

		return result;
	}
	
	
	// delete Sorteo ---------------------------------------------------------------
	@RequestMapping(value = "admin/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute Sorteo sorteo, BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.sorteoService.delete(sorteo);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView(sorteo, "sorteo.commit.errorDelete");
		}

		return result;
	}

	// Compute Winners ---------------------------------------------------------------
	@RequestMapping(value = "admin/winner", method = RequestMethod.GET)
	public ModelAndView elegirGanadores(@RequestParam int sorteoId) {
		this.sorteoService.computeWinner(sorteoId);
		return this.display(sorteoId);

	}
	// Other methods ---------------------------------------------------------------
	protected ModelAndView createEditModelAndView(Sorteo sorteo) {
		assert sorteo != null;
		ModelAndView result;

		result = this.createEditModelAndView(sorteo, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Sorteo sorteo, String message) {
		assert sorteo != null;
		Collection<Premio> premios = this.premioService.findAll();

		ModelAndView result;

		result = new ModelAndView("sorteo/admin/edit");
		result.addObject("sorteo", sorteo);
		result.addObject("message", message);
		result.addObject("premios", premios);
		return result;
	}

}
