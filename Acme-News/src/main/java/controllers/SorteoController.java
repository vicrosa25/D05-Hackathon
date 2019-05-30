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

	//constructor
	public SorteoController() {
		super();

	}

	//Services
	@Autowired
	private SorteoService sorteoService;

	@Autowired
	private PremioService premioService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}



	// Create & Edit Event ---------------------------------------------------------------

	@RequestMapping(value = "admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sorteo sorteo;

		sorteo = this.sorteoService.create();
		result = this.createEditModelAndView2(sorteo);

		return result;
	}

	@RequestMapping(value = "admin/elegirGanadores", method = RequestMethod.GET)
	public ModelAndView elegirGanadores() {
		this.sorteoService.computeWinners();
		return this.listAdmin();

	}

	@RequestMapping(value = "admin/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sorteoId) {
		ModelAndView result;
		Sorteo sorteo;

		sorteo = this.sorteoService.getSorteoRepository().findOne(sorteoId);
		result = this.createEditModelAndView2(sorteo);
		return result;
	}


	// save event ---------------------------------------------------------------

	@RequestMapping(value = "admin/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Sorteo sorteo, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().toString());
			result = this.createEditModelAndView2(sorteo);
		} else {
			try {
				this.sorteoService.save(sorteo);
				result = new ModelAndView("redirect:listAdmin.do");
			} catch (Throwable oops) {
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView2(sorteo, "sorteo.commit.error");
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
			result = new ModelAndView("redirect:listAdmin.do");
		} catch (Throwable oops) {
			result = this.createEditModelAndView2(sorteo, "sorteo.commit.errorDelete");
		}

		return result;
	}
	// Listing ----------------------------------------------------------------

	@RequestMapping("admin/listAdmin")
	public ModelAndView listAdmin() {
		ModelAndView result;
		Collection<Sorteo> sorteos;

		sorteos = this.sorteoService.getSorteoRepository().findAll();
		result = new ModelAndView("sorteo/admin/listAdmin");
		result.addObject("sorteos", sorteos);

		return result;
	}

	// Display ---------------------------------------------------------------

	@RequestMapping("admin/displayAdmin")
	public ModelAndView display(@RequestParam int sorteoId) {
		ModelAndView result;

		result=new ModelAndView("sorteo/admin/displayAdmin");
		Sorteo sorteo = this.sorteoService.getSorteoRepository().findOne(sorteoId);
		result.addObject("sorteo",sorteo);
		result.addObject("premioId",sorteo.getPremio().getId());
		result.addObject("usuarios",sorteo.getUsuarios());
		return result;
	}
	// Other methods ---------------------------------------------------------------

	protected ModelAndView createEditModelAndView2(Sorteo sorteo) {
		assert sorteo != null;
		ModelAndView result;

		result = this.createEditModelAndView2(sorteo, null);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Sorteo sorteo, String message) {
		assert sorteo != null;
		Collection<Premio> premios= this.premioService.findAll();

		ModelAndView result;

		result = new ModelAndView("sorteo/admin/edit");
		result.addObject("sorteo", sorteo);
		result.addObject("message", message);
		result.addObject("premios",premios);
		return result;
	}

}