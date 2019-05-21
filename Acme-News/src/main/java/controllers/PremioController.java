package controllers;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
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

	//constructor
	public PremioController() {
		super();
		
	}
	
	//Services
	@Autowired
	private PremioService premioService;
	
		

	// Create & Edit Event ---------------------------------------------------------------		

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
		
		premio = premioService.getPremioRepository().findOne(premioId);		
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
				result = new ModelAndView("redirect:listAdmin.do");
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
			result = new ModelAndView("redirect:listAdmin.do");
		} catch (Throwable oops) {
			result = createEditModelAndView2(premio, "premio.commit.errorPremio");			
		}
		
		return result;
	}
	// Listing ----------------------------------------------------------------
	
	@RequestMapping("admin/listAdmin")
	public ModelAndView listAdmin() {
		ModelAndView result;
		Collection<Premio> premios;
		
		premios = premioService.getPremioRepository().findAll();		
		result = new ModelAndView("premio/admin/listAdmin");
		result.addObject("premios", premios);
		
		return result;
	}
	
	// Display ---------------------------------------------------------------
	
	@RequestMapping("admin/displayAdmin")
	public ModelAndView display(@RequestParam int premioId) {
		ModelAndView result;
		
		result=new ModelAndView("premio/admin/displayAdmin");
		Premio premio = premioService.getPremioRepository().findOne(premioId);
		
		result.addObject("premio",premio);

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