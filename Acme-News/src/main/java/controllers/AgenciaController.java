package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import repositories.ActorRepository;
import security.LoginService;
import services.AgenciaService;
import domain.Agencia;
import domain.Manager;
import domain.Periodista;

@Controller
@RequestMapping("/agencia")
public class AgenciaController extends AbstractController {

	@Autowired
	AgenciaService agenciaService;
	@Autowired
	private ActorRepository actorRepository;

	// List not full Agencias
	@RequestMapping("/listNotFullAgencia")
	public ModelAndView listNotFull() {
		Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

		ModelAndView result = new ModelAndView("agencia/listNotFullAgencia");
		result.addObject("agencias", notFullAgencia);

		return result;
	}

	// Join to an agency method
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int agenciaId) {

		Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

		ModelAndView result = new ModelAndView("agencia/listNotFullAgencia");
		result.addObject("agencias", notFullAgencia);

		String loggedUsername = LoginService.getPrincipal().getUsername();
		Periodista logged = (Periodista) this.actorRepository
			.findOneByName(loggedUsername);
		if (logged.getAgencia() != null) {
			result.addObject("message", "commit.error.alreadyInAgencia");
		} else {
			this.agenciaService.join(agenciaId);
		}
		return result;
	}

	// Left an agency method
	@RequestMapping(value = "/left", method = RequestMethod.GET)
	public ModelAndView left() {
		Collection<Agencia> notFullAgencia = this.agenciaService.findAllNotFull();

		ModelAndView result = new ModelAndView("agencia/listNotFullAgencia");
		result.addObject("agencias", notFullAgencia);

		String loggedUsername = LoginService.getPrincipal().getUsername();
		Periodista logged = (Periodista) this.actorRepository
			.findOneByName(loggedUsername);
		if (logged.getAgencia() != null) {
			this.agenciaService.left(logged.getAgencia().getId());
		} else {
			result.addObject("message", "commit.error.agencia");
		}
		return result;
	}

	// List Agencias for Manager
	@RequestMapping("/listAgencia")
	public ModelAndView list() {
		Collection<Agencia> agencias = this.agenciaService.findAll();

		ModelAndView result = new ModelAndView("agencia/listAgencia");
		result.addObject("agencias", agencias);

		String loggedUsername = LoginService.getPrincipal().getUsername();
		Manager logged = (Manager) this.actorRepository
			.findOneByName(loggedUsername);
		result.addObject("logged", logged);

		return result;
	}

	// update an agency method
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam int agenciaId) {
		Agencia agencia = this.agenciaService.findOne(agenciaId);

		ModelAndView result = new ModelAndView("agencia/agenciaForm");
		result.addObject("agencia", agencia);
		result.addObject("actualCapacity", agencia.getPeriodistas().size());

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
		Agencia agencia = new Agencia();

		String loggedUsername = LoginService.getPrincipal().getUsername();
		Manager manager = (Manager) this.actorRepository
			.findOneByName(loggedUsername);

		agencia.setImportancia((long) 1);
		agencia.setManager(manager);

		ModelAndView result = new ModelAndView("agencia/createAgencia");
		result.addObject("agencia", agencia);

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
		ModelAndView result = new ModelAndView(
			"redirect:../agencia/listAgencia.do");
		this.agenciaService.delete(agenciaId);
		return result;
	}
}
