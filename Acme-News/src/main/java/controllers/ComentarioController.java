package controllers;








import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Informacion;
import domain.Noticia;
import domain.Comentario;

import services.InformacionService;
import services.ComentarioService;





@Controller
@RequestMapping("/comentario")
public class ComentarioController extends AbstractController {

	//constructor
	public ComentarioController() {
		super();
		
	}
	
	//Services
	
	@Autowired
	private ComentarioService comentarioService;



	
	@Autowired
	private InformacionService informacionService;
		

	
	
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int informacionId) {
			
			ModelAndView result;
			Comentario comentario;
			Informacion informacion= informacionService.findOne(informacionId);
		
			comentario = comentarioService.create(informacion);
			result = new ModelAndView("comentario/edit");
			result.addObject("comentario", comentario);
			System.out.println(informacion);
			System.out.println(informacionId);
			System.out.println("hola");
			System.out.println(comentario.getInformacion());

			return result;
		}



		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Comentario comentario, BindingResult binding) {
			ModelAndView result;
			
			
			
			if (binding.hasErrors()) {
				System.out.println(binding.getAllErrors().toString());
				result = createEditModelAndView(comentario);
			
				
			} else {
				try {
					comentarioService.save(comentario);
					System.out.println("hola2");
					String redirect=null;
					if(comentario.getInformacion() instanceof Noticia){
						redirect="redirect:/noticia/display.do?noticiaId=";
					}else{
						 redirect="redirect:/evento/display.do?eventoId=";
					}
					
					
					redirect=redirect+comentario.getInformacion().getId();
					result = new ModelAndView(redirect);
//					result = createEditModelAndView(comentario,"exito");
					System.out.println(redirect);
					
				} catch (Throwable oops) {
				
					result = createEditModelAndView(comentario, "comentario.commit.error");
				}
			
			}
			return result;
		}


		
	
		
		

		// Ancillary Methods--------------------------
		protected ModelAndView createEditModelAndView(Comentario comentario) {
			ModelAndView result;
			result = createEditModelAndView(comentario, null);
			return result;
		}

		protected ModelAndView createEditModelAndView(Comentario comentario, String message) {
			ModelAndView result;
			result = new ModelAndView("comentario/edit");
			result.addObject("comentario", comentario);
			result.addObject("message", message);
			
			
			
			return result;
		}
		
		




}

