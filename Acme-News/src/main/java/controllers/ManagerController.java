
package controllers;

import java.util.Collection;
import java.util.List;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Manager;
import services.ManagerService;
import utilities.Md5;

@Controller
@RequestMapping("/manager")
public class ManagerController extends AbstractController {

	@Autowired
	private ManagerService managerService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		JOptionPane.showMessageDialog(null, "Forbidden operation");
		return new ModelAndView("redirect:/");
	}

	// List -------------------------------------------------------------
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Manager> managers;

		managers = this.managerService.findAll();

		result = new ModelAndView("manager/admin/list");
		result.addObject("managers", managers);
		result.addObject("requestURI", "manager/admin/list.do");

		return result;
	}

	// Create -----------------------------------------------------------
	@RequestMapping(value = "/admin/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Manager manager;

		manager = this.managerService.create();

		result = new ModelAndView("manager/admin/create");
		result.addObject("manager", manager);

		return result;
	}

	// Save -----------------------------------------------------------
	@RequestMapping(value = "/admin/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Manager manager, final BindingResult binding) {
		ModelAndView result;
		String password;
		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("manager/admin/create");
			result.addObject("manager", manager);
		} else
			try {
				password = Md5.encodeMd5(manager.getUserAccount().getPassword());
				manager.getUserAccount().setPassword(password);
				this.managerService.save(manager);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = new ModelAndView("manager/admin/create");
				result.addObject("manager", manager);
			}
		return result;
	}

	// Update -----------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Manager manager;

		manager = this.managerService.findByPrincipal();
		result = new ModelAndView("manager/update");
		result.addObject("manager", manager);

		return result;
	}

	// Save Update ----------------------------------------------------------
	@RequestMapping(value = "/update", method = RequestMethod.POST, params = "update")
	public ModelAndView edit(@Valid final Manager manager, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = new ModelAndView("manager/update");
			result.addObject("manager", manager);
		} else
			try {
				this.managerService.update(manager);
				result = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				oops.printStackTrace();
				result = new ModelAndView("manager/update");
				result.addObject("manager", manager);
				result.addObject("message", "manager.commit.error");
			}
		return result;
	}

}
