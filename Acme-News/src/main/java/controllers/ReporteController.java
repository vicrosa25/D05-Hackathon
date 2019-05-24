package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NoticiaService;
import services.ReporteService;
import domain.Noticia;
import domain.Reporte;

@Controller
@RequestMapping("/reporte")
public class ReporteController extends AbstractController {

	//constructor
	public ReporteController() {
		super();
	}

	//Services
	@Autowired
	private ReporteService reporteService;

	@Autowired
	private NoticiaService noticiaService;

	/** METHODS **/
	// Create ----------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int noticiaId) {
		ModelAndView result;
		Reporte reporte;
		try{
			Noticia noticia= this.noticiaService.findOne(noticiaId);
			reporte = this.reporteService.create(noticia);
			result = new ModelAndView("reporte/edit");
			result.addObject("reporte", reporte);
		}catch(Throwable oops){
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}
		return result;
	}

	// Edit ------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Reporte reporte, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().toString());
			result = this.createEditModelAndView(reporte);
		} else {
			try {
				this.reporteService.save(reporte);
				String redirect="redirect:/noticia/display.do?noticiaId=";
				redirect=redirect+reporte.getNoticia().getId();
				result = new ModelAndView(redirect);
			} catch (Throwable oops) {
				result = this.createEditModelAndView(reporte, "reporte.commit.error");
			}
		}
		return result;
	}

	// Ancillary Methods--------------------------
	protected ModelAndView createEditModelAndView(Reporte reporte) {
		ModelAndView result;
		result = this.createEditModelAndView(reporte, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Reporte reporte, String message) {
		ModelAndView result;
		result = new ModelAndView("reporte/edit");
		result.addObject("reporte", reporte);
		result.addObject("message", message);
		return result;
	}
}