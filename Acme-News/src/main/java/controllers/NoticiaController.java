package controllers;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NoticiaService;
import services.PeriodistaService;
import utilities.Search;
import domain.Categoria;
import domain.Noticia;
import domain.Periodista;

@Controller
@RequestMapping("/noticia")
public class NoticiaController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public NoticiaController() {
		super();
	}
	
	//Services
	@Autowired
	private NoticiaService noticiaService;
	
	@Autowired
	private PeriodistaService periodistaService;	
	// Listas de categorias ---------------------------------------------------------------		

	@RequestMapping(value="/listaDeportes",method=RequestMethod.GET)
	public ModelAndView listaDeportes() {
		ModelAndView result;
		Collection<Noticia> noticiasDeporte;
		
		noticiasDeporte = this.noticiaService.buscarPorCategoria(Categoria.DEPORTES);

		result = new ModelAndView("noticia/listaDeportes");
		result.addObject("noticias", noticiasDeporte);
		result.addObject("requestURI", "noticia/listaDeportes.do");

		return result;
	}
	
	@RequestMapping(value="/listaEconomia",method=RequestMethod.GET)
	public ModelAndView listaEconomia() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.ECONOMIA);

		result = new ModelAndView("noticia/listaEconomia");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaEconomia.do");

		return result;
	}
	
	@RequestMapping(value="/listaCultura",method=RequestMethod.GET)
	public ModelAndView listaCultura() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.CULTURA);

		result = new ModelAndView("noticia/listaCultura");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaCultura.do");

		return result;
	}
	
	@RequestMapping(value="/listaEspana",method=RequestMethod.GET)
	public ModelAndView listaEspana() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.ESPAÑA);

		result = new ModelAndView("noticia/listaEspana");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaEspana.do");

		return result;
	}
	
	@RequestMapping(value="/listaInternacional",method=RequestMethod.GET)
	public ModelAndView listaInternacional() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.INTERNACIONAL);

		result = new ModelAndView("noticia/listaInternacional");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaInternacional.do");

		return result;
	}
	
	@RequestMapping(value="/listaJuegos",method=RequestMethod.GET)
	public ModelAndView listaJuegos() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.JUEGOS);

		result = new ModelAndView("noticia/listaJuegos");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaJuegos.do");

		return result;
	}
	
	@RequestMapping(value="/listaMotor",method=RequestMethod.GET)
	public ModelAndView listaMotor() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.MOTOR);

		result = new ModelAndView("noticia/listaMotor");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaMotor.do");

		return result;
	}
	
	@RequestMapping(value="/listaOcio",method=RequestMethod.GET)
	public ModelAndView listaOcio() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.OCIO);

		result = new ModelAndView("noticia/listaOcio");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaOcio.do");

		return result;
	}
	
	@RequestMapping(value="/listaOtros",method=RequestMethod.GET)
	public ModelAndView listaOtros() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.buscarPorCategoria(Categoria.OTROS);

		result = new ModelAndView("noticia/listaOtros");
		result.addObject("noticias", noticias);
		result.addObject("requestURI", "noticia/listaOtros.do");

		return result;
	}
	
	@RequestMapping(value="/misNoticias",method=RequestMethod.GET)
	public ModelAndView misNoticias() {
		ModelAndView result;
		Collection<Noticia> noticias;
		Periodista periodista;
				
		periodista = this.periodistaService.findByPrincipal();
		noticias = this.noticiaService.buscarPorPeriodista(periodista.getId());

		result = new ModelAndView("noticia/misNoticias");
		result.addObject("noticias", noticias);

		return result;
	}
	
	@RequestMapping(value="/listBanear",method=RequestMethod.GET)
	public ModelAndView listBanear() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.getNoticiasParaBanear();

		result = new ModelAndView("noticia/listBanear");
		result.addObject("noticias", noticias);

		return result;
	}
	
	@RequestMapping(value="/listPendientes",method=RequestMethod.GET)
	public ModelAndView listPendientes() {
		ModelAndView result;
		Collection<Noticia> noticias;
		
		noticias = this.noticiaService.getNoticiasPendientes();

		result = new ModelAndView("noticia/listPendientes");
		result.addObject("noticias", noticias);

		return result;
	}
	
	// Borrar --------------------------------------------------------------------------------------
	@RequestMapping(value="/borrar",method=RequestMethod.GET)
	public ModelAndView borrar(@RequestParam int noticiaId) {
		ModelAndView result;
		
		Noticia noticia = noticiaService.findOne(noticiaId);
		this.noticiaService.delete(noticia);
		
		result=new ModelAndView("redirect:misNoticias.do");

		return result;	
	}
	// Display --------------------------------------------------------------------------------------
	@RequestMapping(value="/display",method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int noticiaId) {
		ModelAndView result;
		
		result=new ModelAndView("noticia/display");
		Noticia noticia = noticiaService.findOneToDisplay(noticiaId);

		result.addObject("noticia",noticia);

		return result;	
	}
	
	//Buscar por palabra clave ---------------------------------------------------------

	@RequestMapping(value="/buscar",method=RequestMethod.GET)
	public ModelAndView buscar(){
		ModelAndView result;
		
		result=new ModelAndView("noticia/buscar");
		Search search = new Search();
		result.addObject("search", search);
		
		return result;
	}
	@RequestMapping(value = "/buscar", method = RequestMethod.POST, params = "search")
	public ModelAndView search(@NotNull Search search, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result = new ModelAndView("redirect:buscar.do");
		}else{
			result = new ModelAndView("noticia/busqueda");
			result.addObject("noticias", this.noticiaService.buscarPorPalabraClave(search.getWord()));
			result.addObject("requestURI", "noticia/busqueda.do");
		}
		return result;
	}

	
	// CREAR -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public ModelAndView crear() {
		ModelAndView result;
		Noticia noticia;

		noticia = noticiaService.create();
		result = new ModelAndView("noticia/crear");
		result.addObject("noticia", noticia);

		return result;
	}
	
	@RequestMapping(value = "/crear", method = RequestMethod.POST, params = "save")
	public ModelAndView guardar(@Valid Noticia noticia, BindingResult binding) {
		ModelAndView result;	

		if (binding.hasErrors()) {
			for(ObjectError error:binding.getAllErrors()){
				System.out.println(error.getDefaultMessage());
			}
			result = new ModelAndView("noticia/crear");
			result.addObject("noticia", noticia);
		} else {
			try {			
				noticiaService.saveNew(noticia);
				result = new ModelAndView("redirect:misNoticias.do");
			} catch (Throwable oops) {
				System.out.println(oops.getMessage());
				result = new ModelAndView("noticia/crear");
				result.addObject("noticia", noticia);			
			}
		}
		
		return result;
	}
	
	// reportes --------------------------------------------------------------------------------------
	@RequestMapping(value="/reportes",method=RequestMethod.GET)
	public ModelAndView reportes(@RequestParam int noticiaId) {
		ModelAndView result;
		
		result=new ModelAndView("noticia/reportes");
		Noticia noticia = noticiaService.findOne(noticiaId);

		result.addObject("noticia",noticia);

		return result;	
	}
	
	// Banear --------------------------------------------------------------------------------------
	@RequestMapping(value="/banear",method=RequestMethod.GET)
	public ModelAndView banear(@RequestParam int noticiaId) {
		ModelAndView result;
		
		Noticia noticia = noticiaService.findOne(noticiaId);
		this.noticiaService.banearNoticia(noticia);
		
		result=new ModelAndView("redirect:listBanear.do");

		return result;	
	}
	
	// ACEPTAR Y DENEGAR -------------------------------------------------------------------------------
	@RequestMapping(value="/aceptar",method=RequestMethod.GET)
	public ModelAndView aceptar(@RequestParam int noticiaId) {
		ModelAndView result;
		
		Noticia noticia = noticiaService.findOne(noticiaId);
		this.noticiaService.aceptarNoticia(noticia);
		
		result=new ModelAndView("redirect:listPendientes.do");

		return result;	
	}
	
	@RequestMapping(value="/denegar",method=RequestMethod.GET)
	public ModelAndView denegar(@RequestParam int noticiaId) {
		ModelAndView result;
		
		Noticia noticia = noticiaService.findOne(noticiaId);
		this.noticiaService.denegarNoticia(noticia);
		
		result=new ModelAndView("redirect:listPendientes.do");

		return result;	
	}
	
}