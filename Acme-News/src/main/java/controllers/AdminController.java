
package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Administrador;
import domain.Configurations;
import services.AdminService;
import services.ConfigurationsService;
import utilities.Md5;

@Controller
@RequestMapping("/administrator")
public class AdminController extends AbstractController {

	@Autowired
	private AdminService			adminService;

	@Autowired
	private ConfigurationsService	configurationsService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Administrador> admins;

		admins = this.adminService.findAll();

		result = new ModelAndView("administrator/list");
		result.addObject("administrators", admins);
		result.addObject("requestURI", "administrator/list.do");

		return result;
	}

	// Create -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Administrador admin;

		admin = this.adminService.create();

		result = new ModelAndView("administrator/create");
		result.addObject("administrador", admin);

		return result;
	}

	// Save -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Administrador admin, BindingResult binding) {
		ModelAndView result;
		String password;
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("administrator/create");
			result.addObject("administrator", admin);
		} else
			try {
				password = Md5.encodeMd5(admin.getUserAccount().getPassword());
				admin.getUserAccount().setPassword(password);
				this.adminService.save(admin);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = new ModelAndView("administrator/create");
				result.addObject("administrator", admin);
			}
		return result;
	}

	// Update -----------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Administrador administrador;

		administrador = this.adminService.findByPrincipal();
		result = new ModelAndView("administrator/update");
		result.addObject("administrador", administrador);

		return result;
	}

	// Save Update ----------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "update")
	public ModelAndView edit(@Valid Administrador administrador, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("administrator/update");
			result.addObject("administrador", administrador);
		} else
			try {
				this.adminService.update(administrador);
				result = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = new ModelAndView("administrator/update");
				result.addObject("administrador", administrador);
				result.addObject("message", "administrator.commit.error");
			}
		return result;
	}

	/**
	 * 
	 * Manage BANNERS ****************************************************************************
	 */
	@RequestMapping("/dashboard")
	public ModelAndView dashboard() {
		ModelAndView result;
		//query1
		Object[] query1 = this.adminService.query1();

		//query2
		List<String> query2 = this.adminService.query2();

		//query3
		Integer query3_1 = this.adminService.query3_1();
		Integer query3_2 = this.adminService.query3_2();
		Integer query3_3 = this.adminService.query3_3();

		//query4
		Object[] query4 = this.adminService.query4();

		//query5
		Integer query5 = this.adminService.query5();

		//query6
		List<String> query6 = this.adminService.query6();

		//query7
		List<String> query7 = this.adminService.query7();

		//query8
		Integer query8 = this.adminService.query8();

		//query9
		Integer query9 = this.adminService.query9();
		
		//query10
		Integer query10 = this.adminService.query10();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("query1", query1);
		result.addObject("query2", query2);
		result.addObject("query3_1", query3_1);
		result.addObject("query3_2", query3_2);
		result.addObject("query3_3", query3_3);
		result.addObject("query4", query4);
		result.addObject("query5", query5);
		result.addObject("query6", query6);
		result.addObject("query7", query7);
		result.addObject("query8", query8);
		result.addObject("query9", query9);
		result.addObject("query10", query10);
		
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
