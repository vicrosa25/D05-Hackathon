package controllers;

import java.util.Collection;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AgenciaService;
import services.ManagerService;
import services.PeriodistaService;
import domain.Agencia;
import domain.Periodista;

@Controller
@RequestMapping("/agencia")
public class AgenciaController extends AbstractController {

	@Autowired
	AgenciaService agenciaService;
	@Autowired
	private PeriodistaService periodistaService;
	@Autowired
	private ManagerService managerService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// List not full Agencias
	@RequestMapping("/listNotFullAgencia")
	public ModelAndView listNotFull() {
		ModelAndView result;
		try{
			Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

			result = new ModelAndView("agencia/listNotFullAgencia");
			result.addObject("agencias", notFullAgencia);
			result.addObject("agencia", this.periodistaService.findByPrincipal().getAgencia());
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Join to an agency method
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int agenciaId) {
		ModelAndView result;
		try{
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
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Left an agency method
	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public ModelAndView left() {
		ModelAndView result;
		try{
			Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

			result = new ModelAndView("agencia/listNotFullAgencia");
			result.addObject("agencias", notFullAgencia);

			Periodista logged = this.periodistaService.findByPrincipal();
			if (logged.getAgencia() != null) {
				this.agenciaService.left(logged.getAgencia().getId());
			} else {
				result.addObject("message", "commit.error.agencia");
			}
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// List Agencias for Manager
	@RequestMapping("/listAgencia")
	public ModelAndView list() {
		ModelAndView result;
		try{
			Collection<Agencia> agencias = this.agenciaService.findAll();

			result = new ModelAndView("agencia/listAgencia");
			result.addObject("agencias", agencias);
			result.addObject("logged",  this.managerService.findByPrincipal());

		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// update an agency method
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam int agenciaId) {
		ModelAndView result;
		try{
			Agencia agencia = this.agenciaService.findOne(agenciaId);

			result = new ModelAndView("agencia/agenciaForm");
			result.addObject("agencia", agencia);
			result.addObject("actualCapacity", agencia.getPeriodistas().size());

		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	@RequestMapping(value = "/agenciaForm", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Agencia agencia, BindingResult binding) {
		ModelAndView result = new ModelAndView("agencia/agenciaForm");
		agencia = this.agenciaService.reconstruct(agencia, binding);
		if (binding.hasErrors()) {
			result.addObject("agencia", agencia);
			result.addObject("actualCapacity", agencia.getPeriodistas().size());
			result.addObject("message", "commit.error.agencia");
		} else {
			try {
				this.agenciaService.save(agencia);
				result = new ModelAndView("redirect:../agencia/listAgencia.do");
			} catch (Throwable oops) {
				result.addObject("agencia", agencia);
				result.addObject("actualCapacity", agencia.getPeriodistas().size());
				result.addObject("message", "agencia.commit.error");
			}
		}

		return result;
	}

	//Create an agency
	@RequestMapping(value = "/createAgencia", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		try{
			Agencia agencia = new Agencia();

			agencia.setImportancia((long) 1);
			agencia.setManager(this.managerService.findByPrincipal());

			result = new ModelAndView("agencia/createAgencia");
			result.addObject("agencia", agencia);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}
	@RequestMapping(value = "/createAgencia", method = RequestMethod.POST, params = "save")
	public ModelAndView create(Agencia agencia, BindingResult binding) {
		ModelAndView result = new ModelAndView("agencia/createAgencia");
		agencia = this.agenciaService.reconstruct(agencia, binding);
		if (binding.hasErrors()) {
			result.addObject("agencia", agencia);
			result.addObject("actualCapacity", agencia.getPeriodistas().size());
			result.addObject("message", binding.getAllErrors());
			//result.addObject("message", "commit.error.agencia");
		} else {
			try {
				this.agenciaService.save(agencia);
				result = new ModelAndView("redirect:../agencia/listAgencia.do");
			} catch (Throwable oops) {
				result.addObject("agencia", agencia);
				result.addObject("actualCapacity", agencia.getPeriodistas().size());
				result.addObject("message", oops.getMessage());
				//result.addObject("message", "commit.error.agencia");
			}
		}

		return result;
	}


	// Delete an agency method
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int agenciaId) {
		ModelAndView result;
		try{
			result = new ModelAndView(
				"redirect:../agencia/listAgencia.do");
			this.agenciaService.delete(agenciaId);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}
}
