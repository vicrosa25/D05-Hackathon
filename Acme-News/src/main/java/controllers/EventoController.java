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

import services.EventoService;
import services.ManagerService;
import domain.Comentario;
import domain.Evento;
import domain.Manager;


@Controller
@RequestMapping("/evento")
public class EventoController extends AbstractController {

	//constructor
	public EventoController() {
		super();

	}

	//Services
	@Autowired
	private EventoService eventoService;

	@Autowired
	private ManagerService managerService;



	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value="/allEventos", method = RequestMethod.GET)
	public ModelAndView allEvents(){
		ModelAndView result;
		Collection<Evento> eventos;
		eventos = this.eventoService.findActualEvents();
		result=new ModelAndView("evento/allEventos");
		result.addObject("eventos", eventos);
		return result;
	}

	@RequestMapping(value="/manager/misEventos",method=RequestMethod.GET)
	public ModelAndView misEventos() {
		ModelAndView result;
		Collection<Evento> eventos;
		Manager manager;
		try{
			manager = this.managerService.findByPrincipal();
			eventos = this.eventoService.buscarPorManager(manager);

			result = new ModelAndView("evento/manager/misEventos");
			result.addObject("eventos", eventos);
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result= super.forbiddenOpperation();
		}

		return result;
	}

	// Display --------------------------------------------------------------------------------------
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam int eventoId) {
		ModelAndView result;
		try{
			Evento evento = this.eventoService.findOne(eventoId);
			Collection<Comentario> comentarios = evento.getComentarios();
			result=new ModelAndView("evento/display");
			result.addObject("evento",evento);
			result.addObject("comentarios", comentarios );
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result= super.forbiddenOpperation();
		}
		return result;
	}

	// Borrar --------------------------------------------------------------------------------------
	@RequestMapping(value="/manager/borrar",method=RequestMethod.GET)
	public ModelAndView borrar(@RequestParam int eventoId) {
		ModelAndView result;
		try{
			Evento evento = this.eventoService.findOne(eventoId);
			this.eventoService.delete(evento);

			result=new ModelAndView("redirect:misEventos.do");
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result= super.forbiddenOpperation();
		}

		return result;
	}

	// CREAR -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/manager/crear", method = RequestMethod.GET)
	public ModelAndView crear() {
		ModelAndView result;
		Evento evento;

		try{
			Manager manager = this.managerService.findByPrincipal();

			evento = this.eventoService.create();
			result = new ModelAndView("evento/manager/crear");
			result.addObject("evento", evento);
			result.addObject("agencias", manager.getAgencias());
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result= super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/manager/crear", method = RequestMethod.POST, params = "save")
	public ModelAndView guardar(@Valid Evento evento, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = new ModelAndView("evento/manager/crear");
			result.addObject("evento", evento);
			result.addObject("agencias", this.managerService.findByPrincipal().getAgencias());
		} else {
			try {
				this.eventoService.saveNew(evento);
				result = new ModelAndView("redirect:misEventos.do");
			} catch (Throwable oops) {
				oops.printStackTrace();
				System.out.println(oops.getMessage());
				result = new ModelAndView("evento/manager/crear");
				result.addObject("agencias", this.managerService.findByPrincipal().getAgencias());
				result.addObject("evento", evento);
			}
		}

		return result;
	}
}