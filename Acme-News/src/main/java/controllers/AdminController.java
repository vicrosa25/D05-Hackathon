package controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Configurations;
import repositories.AdminRepository;
import services.ConfigurationsService;

@Controller
@RequestMapping("/administrator")
public class AdminController extends AbstractController {


	@Autowired
	private AdminRepository adminRepository;
	
	
	@Autowired
	private ConfigurationsService	configurationsService;
	
	
	
	// Constructors -----------------------------------------------------------
	public AdminController() {
		super();
	}
	
	
	
	
	
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;
		//query1
		Object[] query1=this.adminRepository.getQueryH1();
		//query2
		List<String> query2=this.adminRepository.getQueryH2();
		//query3
		Integer query3_1=this.adminRepository.getQueryh3_1();
		Integer query3_2=this.adminRepository.getQueryh3_2();
		Integer query3_3=this.adminRepository.getQueryh3_3();
		//query4
		Object[] query4=this.adminRepository.getQueryH4();

		//query5
		Integer query5=this.adminRepository.getQueryH5();
		//query6
		List<String> query6=this.adminRepository.getQueryH6();

		//query7
		List<String> query7=this.adminRepository.getQueryH7();


		//query8
		Integer query8=this.adminRepository.getQueryH8();
		//query9
		Integer query9=this.adminRepository.getQueryH9();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("query1",query1);
		result.addObject("query2",query2);
		result.addObject("query3_1",query3_1);
		result.addObject("query3_2",query3_2);
		result.addObject("query3_3",query3_3);
		result.addObject("query4",query4);
		result.addObject("query5",query5);
		result.addObject("query6",query6);
		result.addObject("query7",query7);
		result.addObject("query8",query8);
		result.addObject("query9",query9);
		return result;
	}
	
	
	/**
	 * 
	 * Manage CACHE ****************************************************************************
	 */

	// Configurations cache -------------------------------------------------------------
	@RequestMapping(value = "/config/cache/edit", method = RequestMethod.GET)
	public ModelAndView cache() {
		ModelAndView result;
		Configurations configurations;

		configurations = this.configurationsService.getConfiguration();
		result = new ModelAndView("administrator/config/cache/edit");
		result.addObject("configurations", configurations);

		return result;
	}

	@RequestMapping(value = "/config/cache/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView cache(@Valid Configurations configurations, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("administrator/config/cache/edit");
			result.addObject("configurations", configurations);
		} else
			try {
				this.configurationsService.update(configurations);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = new ModelAndView("administrator/config/cache/edit");
				result.addObject("configurations", configurations);
				result.addObject("message", "administrator.commit.error");
			}

		return result;
	}
	
	/**
	 * 
	 * Alive config ****************************************************************************
	 */

	// Alive Configurations -------------------------------------------------------------
	@RequestMapping(value = "/config/aliveConfig/edit", method = RequestMethod.GET)
	public ModelAndView config() {
		ModelAndView result;
		Configurations configurations;

		configurations = this.configurationsService.getConfiguration();
		result = new ModelAndView("administrator/config/aliveConfig/edit");
		result.addObject("configurations", configurations);

		return result;
	}

	@RequestMapping(value = "/config/aliveConfig/edit", method = RequestMethod.POST, params = "update")
	public ModelAndView config(@Valid final Configurations configurations, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("administrator/config/aliveConfig/edit");
			result.addObject("configurations", configurations);
		} else
			try {
				this.configurationsService.update(configurations);
				result = new ModelAndView("redirect:/");
			} catch (final Throwable oops) {
				result = new ModelAndView("administrator/config/aliveConfig/edit");
				result.addObject("configurations", configurations);
				result.addObject("message", "administrator.commit.error");
			}

		return result;
	}




}
