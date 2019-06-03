package controllers;

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

import services.ComentarioService;
import services.InformacionService;
import domain.Comentario;
import domain.Informacion;
import domain.Noticia;

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


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	/** METHODS **/
	// Comentar --------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int informacionId) {
		ModelAndView result;
		Comentario comentario;
		try{
			Informacion informacion= this.informacionService.findOne(informacionId);

			comentario = this.comentarioService.create(informacion);
			result = new ModelAndView("comentario/edit");
			result.addObject("comentario", comentario);
		}catch(Throwable oops){
			result = super.forbiddenOpperation();
			oops.printStackTrace();
			System.out.println(oops.getMessage());
		}
		return result;
	}


	// Edit ------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comentario comentario, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().toString());
			result = this.createEditModelAndView(comentario);
		} else {
			try {
				comentario = this.comentarioService.save(comentario);
				String redirect=null;
				if(comentario.getInformacion() instanceof Noticia){
					redirect="redirect:/noticia/display.do?noticiaId=";
				}else{
					redirect="redirect:/evento/display.do?eventoId=";
				}
				redirect=redirect+comentario.getInformacion().getId();
				result = new ModelAndView(redirect);

			} catch (Throwable oops) {
				oops.printStackTrace();
				System.out.println(oops.getMessage());
				result = this.createEditModelAndView(comentario, "comentario.commit.error");
			}

		}
		return result;
	}

	// Ancillary Methods--------------------------
	protected ModelAndView createEditModelAndView(Comentario comentario) {
		ModelAndView result;
		result = this.createEditModelAndView(comentario, null);
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