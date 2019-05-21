package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.EventoService;
import services.PeriodistaService;
import domain.Comentario;
import domain.Evento;
import domain.Periodista;


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
	private PeriodistaService periodistaService;


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
	
	@RequestMapping(value="/misEventos",method=RequestMethod.GET)
	public ModelAndView misEventos() {
		ModelAndView result;
		Collection<Evento> eventos;
		Periodista periodista;
				
		periodista = this.periodistaService.findByPrincipal();
		eventos = this.eventoService.buscarPorPeriodista(periodista.getId());

		result = new ModelAndView("evento/misEventos");
		result.addObject("eventos", eventos);

		return result;
	}
	
	// Display --------------------------------------------------------------------------------------
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam int eventoId) {
		ModelAndView result;
		Evento evento = eventoService.findOne(eventoId);
		Collection<Comentario> comentarios = evento.getComentarios(); 
		result=new ModelAndView("evento/display");
		result.addObject("evento",evento);
		result.addObject("comentarios", comentarios );
		return result;	
	}
	
	// Borrar --------------------------------------------------------------------------------------
	@RequestMapping(value="/borrar",method=RequestMethod.GET)
	public ModelAndView borrar(@RequestParam int eventoId) {
		ModelAndView result;
		
		Evento evento = eventoService.findOne(eventoId);
		this.eventoService.delete(evento);
		
		result=new ModelAndView("redirect:misEventos.do");

		return result;	
	}

	// CREAR -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public ModelAndView crear() {
		ModelAndView result;
		Evento evento;
		
		if(this.periodistaService.checkAgencia()){
			evento = eventoService.create();
			result = new ModelAndView("evento/crear");
			result.addObject("evento", evento);
		}else{
			result=new ModelAndView("redirect:misEventos.do");
		}

		return result;
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST, params = "save")
	public ModelAndView guardar(@Valid Evento evento, BindingResult binding) {
		ModelAndView result;	

		if (binding.hasErrors()) {
			result = new ModelAndView("evento/crear");
			result.addObject("evento", evento);
		} else {
			try {			
				eventoService.saveNew(evento);
				result = new ModelAndView("redirect:misEventos.do");
			} catch (Throwable oops) {
				System.out.println(oops.getMessage());
				result = new ModelAndView("evento/crear");
				result.addObject("evento", evento);			
			}
		}
		
		return result;
	}
}