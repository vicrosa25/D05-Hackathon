
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Agencia;
import domain.Manager;
import domain.Periodista;
import services.AgenciaService;
import services.ManagerService;
import services.PeriodistaService;

@Controller
@RequestMapping("/agencia")
public class AgenciaController extends AbstractController {

	@Autowired
	AgenciaService				agenciaService;

	@Autowired
	private PeriodistaService	periodistaService;

	@Autowired
	private ManagerService		managerService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Manager Agencia LIST --------------------------------------------------------------------------------------
	@RequestMapping("/manager/list")
	public ModelAndView list() {
		ModelAndView result;
		Manager principal;
		try {
			Collection<Agencia> agencias = this.agenciaService.findAll();
			principal = this.managerService.findByPrincipal();

			result = new ModelAndView("agencia/manager/list");
			result.addObject("agencias", agencias);
			result.addObject("requestURI", "agencia/manager/list.do");
			result.addObject("logged", principal);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Manager Agencia CREATE ---------------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try {
			Agencia agencia = new Agencia();
			result = this.createEditModelAndView(agencia);
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}
	@RequestMapping(value = "/manager/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@Valid Agencia agencia, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(agencia);
		} else {
			try {
				this.agenciaService.save(agencia);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.createEditModelAndView(agencia);
			}
		}
		return result;
	}

	// Manager Agencia EDIT -------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/edit", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam int agenciaId) {
		ModelAndView result;

		try {
			Agencia agencia = this.agenciaService.findOne(agenciaId);
			result = this.editModelAndView(agencia);
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	@RequestMapping(value = "/manager/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Agencia agencia, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.editModelAndView(agencia);
		} else {
			try {
				this.agenciaService.save(agencia);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				result = this.editModelAndView(agencia);
				result.addObject("message", "agencia.commit.error");
			}
		}

		return result;
	}

	// Delete an agency method ------------------------------------------------------------------------------------
	@RequestMapping(value = "manager/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int agenciaId) {
		ModelAndView result;
		try {
			this.agenciaService.delete(agenciaId);
			//result = new ModelAndView("redirect:manager/list.do");
			result = this.list();
		} catch (Throwable oops) {
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Agencia Periodistas LIST   ------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/periodista/list", method = RequestMethod.GET)
	public ModelAndView periodistasList(@RequestParam int agenciaId) {
		ModelAndView result;

		try {
			Agencia agencia = this.agenciaService.findOne(agenciaId);
			result = new ModelAndView("agencia/manager/periodista/list");
			result.addObject("periodistas", agencia.getPeriodistas());
			result.addObject("requestURI", "agencia/manager/periodista/list.do");
			result.addObject("agenciaId", agencia.getId());
		} catch (Throwable oops) {
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Agencia Periodistas LIST   ------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/periodista/eject", method = RequestMethod.GET)
	public ModelAndView periodistasEject(@RequestParam int periodistaId, @RequestParam int agenciaId ) {
		ModelAndView result;
		Agencia agencia;

		try {
			agencia = this.agenciaService.periodistaEject(periodistaId, agenciaId);
			result = new ModelAndView("agencia/manager/periodista/list");
			result.addObject("periodistas", agencia.getPeriodistas());
			result.addObject("requestURI", "agencia/manager/periodista/list.do");
		} catch (Throwable oops) {
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// List not full Agencias ----------------------------------------------------------------------------------------
	@RequestMapping("/listNotFullAgencia")
	public ModelAndView listNotFull() {
		ModelAndView result;
		try {
			Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

			result = new ModelAndView("agencia/listNotFullAgencia");
			result.addObject("agencias", notFullAgencia);
			result.addObject("agencia", this.periodistaService.findByPrincipal().getAgencia());
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Join to an agency method -------------------------------------------------------------------------------------
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int agenciaId) {
		ModelAndView result;
		try {
			Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

			result = new ModelAndView("agencia/listNotFullAgencia");
			result.addObject("agencias", notFullAgencia);
			result.addObject("agencia", this.periodistaService.findByPrincipal().getAgencia());

			Periodista logged = this.periodistaService.findByPrincipal();
			if (logged.getAgencia() != null) {
				result.addObject("message", "commit.error.alreadyInAgencia");
			} else {
				this.agenciaService.join(agenciaId);
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Left an agency method -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public ModelAndView left() {
		ModelAndView result;
		try {
			Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

			result = new ModelAndView("agencia/listNotFullAgencia");
			result.addObject("agencias", notFullAgencia);

			Periodista logged = this.periodistaService.findByPrincipal();
			if (logged.getAgencia() != null) {
				this.agenciaService.left(logged.getAgencia().getId());
			} else {
				result.addObject("message", "commit.error.agencia");
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Other methods-------------------------------------------------------------------------------------------------------
	private ModelAndView createEditModelAndView(Agencia agencia) {
		ModelAndView result;
		result = this.createEditModelAndView(agencia, null);
		return result;
	}

	private ModelAndView createEditModelAndView(Agencia agencia, String message) {
		ModelAndView result;
		result = new ModelAndView("agencia/manager/create");
		result.addObject("action", "agencia/manager/create.do");
		result.addObject("agencia", agencia);
		result.addObject("message", message);
		return result;
	}

	private ModelAndView editModelAndView(Agencia agencia) {
		ModelAndView result;
		result = this.editModelAndView(agencia, null);
		return result;
	}

	private ModelAndView editModelAndView(Agencia agencia, String message) {
		ModelAndView result;
		result = new ModelAndView("agencia/manager/edit");
		result.addObject("action", "agencia/manager/edit.do");
		result.addObject("agencia", agencia);
		result.addObject("capacity", agencia.getCapacidadDisponible());
		result.addObject("message", message);
		return result;
	}
}
