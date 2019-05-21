package controllers;








import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Noticia;
import domain.Reporte;

import services.NoticiaService;
import services.ReporteService;





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
		

	
	
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create(@RequestParam int noticiaId) {
			
			ModelAndView result;
			Reporte reporte;
			Noticia noticia= noticiaService.findOne(noticiaId);
			reporte = reporteService.create(noticia);
			result = new ModelAndView("reporte/edit");
			result.addObject("reporte", reporte);
		
			System.out.println("hola");
			

			return result;
		}



		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Reporte reporte, BindingResult binding) {
			ModelAndView result;
			
			
			
			if (binding.hasErrors()) {
				System.out.println(binding.getAllErrors().toString());
				result = createEditModelAndView(reporte);
			
				
			} else {
				try {
					reporteService.save(reporte);
					System.out.println("hola2");
					
					
					String redirect="redirect:/noticia/display.do?noticiaId=";
					redirect=redirect+reporte.getNoticia().getId();
					result = new ModelAndView(redirect);
//					result = createEditModelAndView(reporte,"exito");
					System.out.println(redirect);
					
				} catch (Throwable oops) {
				
					result = createEditModelAndView(reporte, "reporte.commit.error");
				}
			
			}
			return result;
		}


		
	
		
		

		// Ancillary Methods--------------------------
		protected ModelAndView createEditModelAndView(Reporte reporte) {
			ModelAndView result;
			result = createEditModelAndView(reporte, null);
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

