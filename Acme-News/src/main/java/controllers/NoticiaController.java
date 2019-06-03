package controllers;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Categoria;
import domain.Estado;
import domain.Mensaje;
import domain.Noticia;
import domain.Periodista;
import domain.Usuario;
import forms.EnviarNoticiaForm;
import services.MensajeService;
import services.NoticiaService;
import services.PeriodistaService;
import services.UsuarioService;
import utilities.Search;

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

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private MensajeService mensajeService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Enviar a otro usuario que sigues ----------------------------------------------------
	@RequestMapping(value="/usuario/enviar",method=RequestMethod.GET)
	public ModelAndView enviar(@RequestParam int noticiaId) {
		ModelAndView result;
		try{
			Noticia noticia = this.noticiaService.findOne(noticiaId);
			Assert.isTrue(noticia.getEstado() == Estado.PUBLICADA);
			EnviarNoticiaForm formulario = new EnviarNoticiaForm();
			formulario.setNoticiaId(noticiaId);

			result=new ModelAndView("noticia/usuario/enviar");
			result.addObject("titulo",noticia.getTitulo());
			result.addObject("formulario",formulario);
			result.addObject("usuarios",this.usuarioService.findByPrincipal().getSiguiendo());
		}catch(Throwable oops){
			System.out.println(oops.getMessage());
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value="/usuario/enviar",method=RequestMethod.POST, params = "save")
	public ModelAndView enviar(EnviarNoticiaForm formulario, BindingResult binding) {
		ModelAndView result;
		try{
			if (formulario.getUsuario() == null) {
				binding.rejectValue("usuario", "noticia.enviar.error", "You must select a user");
				result=new ModelAndView("noticia/usuario/enviar");
				result.addObject("formulario",formulario);
				result.addObject("usuarios",this.usuarioService.findByPrincipal().getSiguiendo());
			}else{
				Usuario principal = this.usuarioService.findByPrincipal();
				Noticia noticia = this.noticiaService.findOne(formulario.getNoticiaId());

				Assert.isTrue(noticia.getEstado() == Estado.PUBLICADA);
				Assert.isTrue(principal.getSiguiendo().contains(formulario.getUsuario()));

				Mensaje mensaje = this.mensajeService.create();
				mensaje.setEmisor(principal);
				mensaje.setNoticia(noticia);
				mensaje.setReceptor(formulario.getUsuario());
				mensaje.setTexto(formulario.getTexto());

				this.mensajeService.save(mensaje);

				result=new ModelAndView("redirect:/");
			}
			return result;
		}catch(Throwable oops){
			System.out.println(oops.getMessage());
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}

		return result;
	}
	// Listas de categorias ---------------------------------------------------------------

	@RequestMapping(value="usuario/recibidas",method=RequestMethod.GET)
	public ModelAndView recibidas() {
		ModelAndView result;
		Collection<Mensaje> mensajes;
		try{
			mensajes = this.mensajeService.mensajesRecibidos();

			result = new ModelAndView("noticia/usuario/recibidas");
			result.addObject("mensajes", mensajes);
			result.addObject("requestURI", "noticia/usuario/recibidas.do");
		}catch(Throwable oops){
			System.out.println(oops.getMessage());
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value="/lista",method=RequestMethod.GET)
	public ModelAndView lista() {
		ModelAndView result;
		Collection<Noticia> noticias;
		try{
			noticias = this.noticiaService.findAllPublicadas();

			result = new ModelAndView("noticia/lista");
			result.addObject("noticias", noticias);
			result.addObject("requestURI", "noticia/lista.do");
		}catch(Throwable oops){
			System.out.println(oops.getMessage());
			oops.printStackTrace();
			result = super.forbiddenOpperation();
		}
		return result;
	}

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

		Noticia noticia = this.noticiaService.findOne(noticiaId);
		this.noticiaService.delete(noticia);

		result=new ModelAndView("redirect:misNoticias.do");

		return result;
	}
	// Display --------------------------------------------------------------------------------------
	@RequestMapping(value="/display",method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int noticiaId) {
		ModelAndView result;

		result=new ModelAndView("noticia/display");
		Noticia noticia = this.noticiaService.findOneToDisplay(noticiaId);

		result.addObject("noticia",noticia);
		result.addObject("key", "noticia");

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
			result = new ModelAndView("noticia/lista");
			result.addObject("noticias", this.noticiaService.buscarPorPalabraClave(search.getWord()));
			result.addObject("requestURI", "noticia/lista.do");
		}
		return result;
	}


	// CREAR -----------------------------------------------------------------------------------------
	@RequestMapping(value = "/crear", method = RequestMethod.GET)
	public ModelAndView crear() {
		ModelAndView result;
		Noticia noticia;

		noticia = this.noticiaService.create();
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
				this.noticiaService.saveNew(noticia);
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
		Noticia noticia = this.noticiaService.findOne(noticiaId);

		result.addObject("noticia",noticia);

		return result;
	}

	// Banear --------------------------------------------------------------------------------------
	@RequestMapping(value="/banear",method=RequestMethod.GET)
	public ModelAndView banear(@RequestParam int noticiaId) {
		ModelAndView result;

		Noticia noticia = this.noticiaService.findOne(noticiaId);
		this.noticiaService.banearNoticia(noticia);

		result=new ModelAndView("redirect:listBanear.do");

		return result;
	}

	// ACEPTAR Y DENEGAR -------------------------------------------------------------------------------
	@RequestMapping(value="/aceptar",method=RequestMethod.GET)
	public ModelAndView aceptar(@RequestParam int noticiaId) {
		ModelAndView result;

		Noticia noticia = this.noticiaService.findOne(noticiaId);
		this.noticiaService.aceptarNoticia(noticia);

		result=new ModelAndView("redirect:listPendientes.do");

		return result;
	}

	@RequestMapping(value="/denegar",method=RequestMethod.GET)
	public ModelAndView denegar(@RequestParam int noticiaId) {
		ModelAndView result;

		Noticia noticia = this.noticiaService.findOne(noticiaId);
		this.noticiaService.denegarNoticia(noticia);

		result=new ModelAndView("redirect:listPendientes.do");

		return result;
	}

}