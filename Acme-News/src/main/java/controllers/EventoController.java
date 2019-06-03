
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comentario;
import domain.Evento;
import domain.Manager;
import services.EventoService;
import services.ManagerService;

@Controller
@RequestMapping("/evento")
public class EventoController extends AbstractController {

	//Services
	@Autowired
	private EventoService	eventoService;

	@Autowired
	private ManagerService	managerService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Evento LIST ----------------------------------------------------------------
	@RequestMapping(value = "/allEventos", method = RequestMethod.GET)
	public ModelAndView allEvents() {
		ModelAndView result;
		Collection<Evento> eventos;
		eventos = this.eventoService.findActualEvents();
		result = new ModelAndView("evento/allEventos");
		result.addObject("eventos", eventos);
		return result;
	}

	// Evento Manager LIST
	@RequestMapping(value = "/manager/list", method = RequestMethod.GET)
	public ModelAndView misEventos() {
		ModelAndView result;
		Collection<Evento> eventos;
		Manager manager;
		try {
			manager = this.managerService.findByPrincipal();
			eventos = this.eventoService.buscarPorManager(manager);

			result = new ModelAndView("evento/manager/list");
			result.addObject("requestURI", "evento/manager/list.do");
			result.addObject("eventos", eventos);
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	// Display --------------------------------------------------------------------------------------
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam int eventoId) {
		ModelAndView result;
		try {
			Evento evento = this.eventoService.findOne(eventoId);
			Collection<Comentario> comentarios = evento.getComentarios();
			result = new ModelAndView("evento/display");
			result.addObject("evento", evento);
			result.addObject("comentarios", comentarios);
			result.addObject("key", "evento");
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Manager Evento CREATE -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/create", method = RequestMethod.GET)
	public ModelAndView crear() {
		ModelAndView result;
		Evento evento;

		try {
			evento = this.eventoService.create();
			result = this.createEditModelAndView(evento);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/manager/create", method = RequestMethod.POST, params = "save")
	public ModelAndView guardar(@Valid Evento evento, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = this.createEditModelAndView(evento);
		} else {
			try {
				this.eventoService.save(evento);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView(evento);
			}
		}

		return result;
	}

	// Manager Evento Delete --------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/delete", method = RequestMethod.GET)
	public ModelAndView borrar(@RequestParam int eventoId) {
		ModelAndView result;
		try {
			Evento evento = this.eventoService.findOne(eventoId);
			this.eventoService.delete(evento);

			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	// Other methods-------------------------------------------------------------------------------------------------------
	private ModelAndView createEditModelAndView(Evento evento) {
		ModelAndView result;
		result = this.createEditModelAndView(evento, null);
		return result;
	}

	private ModelAndView createEditModelAndView(Evento evento, String message) {
		ModelAndView result;
		result = new ModelAndView("evento/manager/create");
		result.addObject("agencias", this.managerService.findByPrincipal().getAgencias());
		result.addObject("evento", evento);
		result.addObject("message", message);
		return result;
	}

	//	private ModelAndView editModelAndView(Evento evento) {
	//		ModelAndView result;
	//		result = this.editModelAndView(evento, null);
	//		return result;
	//	}
	//
	//	private ModelAndView editModelAndView(Evento evento, String message) {
	//		ModelAndView result;
	//		result = new ModelAndView("agencia/manager/edit");
	//		result.addObject("action", "agencia/manager/edit.do");
	//		result.addObject("agencia", agencia);
	//		result.addObject("capacity", agencia.getCapacidadDisponible());
	//		result.addObject("message", message);
	//		return result;
	//	}
}
