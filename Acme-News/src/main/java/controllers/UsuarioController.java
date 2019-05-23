package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.InformacionService;
import services.NoticiaService;
import services.PeriodistaService;
import services.SorteoService;
import services.TasaService;
import services.UsuarioService;
import utilities.Md5;
import domain.Estatus;
import domain.Informacion;
import domain.Periodista;
import domain.Sorteo;
import domain.Tasa;
import domain.Usuario;

@Controller
@RequestMapping("/usuario")
public class UsuarioController extends AbstractController {

	// Services
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ActorService actorService;


	@Autowired
	private NoticiaService noticiaService;

	@Autowired
	private SorteoService sorteoService;

	@Autowired
	private TasaService tasaService;

	@Autowired
	private InformacionService informacionService;

	@Autowired
	private PeriodistaService periodistaService;


	// Constructor
	public UsuarioController(){
		super();
	}


	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Usuario usuario = (Usuario) this.actorService.findByPrincipal();
		result = new ModelAndView("usuario/display");
		result.addObject("usuario", usuario);
		return result;
	}


	// Edit --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Usuario usuario = (Usuario) this.actorService.findByPrincipal();
		result = this.createEditModelAndView(usuario);
		return result;
	}

	// Save --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Usuario usuario, BindingResult binding) {
		ModelAndView result;
		String password;

		usuario = this.usuarioService.reconstruct(usuario, binding);
		if(binding.hasErrors()) {
			result = this.createEditModelAndView(usuario);
		} else {
			try {
				password = Md5.encodeMd5(usuario.getUserAccount().getPassword());
				usuario.getUserAccount().setPassword(password);
				this.usuarioService.save(usuario);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch(Throwable oops) {
				result = this.createEditModelAndView(usuario, "periodista.duplicated");
			}
		}

		return result;
	}



	/** Other methods **/

	private ModelAndView createEditModelAndView(Usuario usuario) {
		ModelAndView result;
		result = this.createEditModelAndView(usuario, null);
		return result;
	}

	private ModelAndView createEditModelAndView(Usuario usuario, String message) {
		ModelAndView result;
		result = new ModelAndView("usuario/edit");
		result.addObject("action", "usuario/edit.do");
		result.addObject("modelAttribute", "usuario");
		result.addObject("usuario", usuario);
		result.addObject("message", message);
		return result;
	}

	/** ------- **/


	// Listas ---------------------------------------------------------------
	@RequestMapping(value="/listUsuariosSiguiendo",method=RequestMethod.GET)
	public ModelAndView listUsuariosSiguiendo() {
		ModelAndView result;

		try {
			result = new ModelAndView("usuario/listUsuariosSiguiendo");
			Collection<Usuario> usuariosSiguiendo= new ArrayList<Usuario>();
			Collection<Usuario> usuarios = new ArrayList<Usuario>(this.usuarioService.findAll());

			usuariosSiguiendo=this.usuarioService.usuariosSiguiendo();
			usuarios.removeAll(usuariosSiguiendo);
			usuarios.remove(this.usuarioService.findByPrincipal());

			result.addObject("usuarios", usuarios);
			result.addObject("usuariosSiguiendo", usuariosSiguiendo);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value="/listPeriodistasSiguiendo",method=RequestMethod.GET)
	public ModelAndView listPeriodistasSiguiendo() {
		ModelAndView result;

		try {
			result = new ModelAndView("usuario/listPeriodistasSiguiendo");
			Collection<Periodista> periodistasSiguiendo= new ArrayList<Periodista>();
			Collection<Periodista> periodistas = new ArrayList<Periodista>(this.periodistaService.findAll());

			periodistasSiguiendo = this.usuarioService.findByPrincipal().getPeriodistas();
			periodistas.removeAll(periodistasSiguiendo);

			result.addObject("periodistas", periodistas);
			result.addObject("periodistasSiguiendo", periodistasSiguiendo);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}


	@RequestMapping(value="/listInformacionDeQuienSigues",method=RequestMethod.GET)
	public ModelAndView listCompartidaOtros() {
		ModelAndView result;


		result = new ModelAndView("usuario/listInformacionDeQuienSigues");


		result.addObject("informacionDeQuienSigues", this.usuarioService.findInformacionDeQuienSigues());

		return result;
	}

	// Edition--------------------------------
	@RequestMapping(value = "/seguirUsuario", method = RequestMethod.POST)
	public ModelAndView seguirUsuario(@RequestParam int usuarioId) {
		ModelAndView result;
		Usuario usuario;
		try{
			usuario= this.usuarioService.findOne(usuarioId);
			Assert.notNull(usuario);
			if(this.usuarioService.seguirUsuario(usuario)){
				result = this.createEditModelAndViewSeguir(usuario, "usuario.seguir.exito");
			}else{
				result = this.createEditModelAndViewSeguir(usuario, "usuario.seguir.error");
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/seguirPeriodista", method = RequestMethod.POST)
	public ModelAndView seguirPeriodista(@RequestParam int periodistaId) {
		ModelAndView result;
		Periodista periodista;
		try{
			periodista= this.periodistaService.findOne(periodistaId);
			if(this.usuarioService.seguirPeriodista(periodista)){
				result = this.createEditModelAndViewSeguir(periodista, "periodista.seguir.exito");
			}else{
				result = this.createEditModelAndViewSeguir(periodista, "periodista.seguir.error");
			}

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/noSeguirUsuario", method = RequestMethod.POST)
	public ModelAndView noSeguirUsuario(@RequestParam int usuarioId) {
		ModelAndView result;
		Usuario usuario;
		try{
			usuario= this.usuarioService.findOne(usuarioId);
			Assert.notNull(usuario);
			if(this.usuarioService.noSeguirUsuario(usuario)){
				result = this.createEditModelAndViewSeguir(usuario, "usuario.noSeguir.exito");
			}else{
				result = this.createEditModelAndViewSeguir(usuario, "usuario.noSeguir.error");
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/noSeguirPeriodista", method = RequestMethod.POST)
	public ModelAndView noSeguirPeriodista(@RequestParam int periodistaId) {
		ModelAndView result;
		Periodista periodista;
		try{
			periodista= this.periodistaService.findOne(periodistaId);
			Assert.notNull(periodista);
			if(this.usuarioService.noSeguirPeriodista(periodista)){
				result = this.createEditModelAndViewSeguir(periodista, "periodista.noSeguir.exito");
			}else{
				result = this.createEditModelAndViewSeguir(periodista, "periodista.noSeguir.error");
			}
		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}


	protected ModelAndView createEditModelAndViewSeguir(Usuario usuario) {
		ModelAndView result;
		result = this.createEditModelAndViewSeguir(usuario, null);
		return result;
	}
	protected ModelAndView createEditModelAndViewSeguir(Periodista periodista) {
		ModelAndView result;
		result = this.createEditModelAndViewSeguir(periodista, null);
		return result;
	}

	protected ModelAndView createEditModelAndViewSeguir(Usuario usuario, String message) {
		ModelAndView result;
		try {
			result = new ModelAndView("usuario/listUsuariosSiguiendo");
			Collection<Usuario> usuariosSiguiendo= new ArrayList<Usuario>();
			Collection<Usuario> usuarios = new ArrayList<Usuario>(this.usuarioService.findAll());

			usuariosSiguiendo=this.usuarioService.usuariosSiguiendo();
			usuarios.removeAll(usuariosSiguiendo);
			usuarios.remove(this.usuarioService.findByPrincipal());

			result.addObject("usuarios", usuarios);
			result.addObject("message", message);
			result.addObject("usuariosSiguiendo", usuariosSiguiendo);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}

	protected ModelAndView createEditModelAndViewSeguir(Periodista periodista, String message) {
		ModelAndView result;
		try {
			result = new ModelAndView("usuario/listPeriodistasSiguiendo");
			Collection<Periodista> periodistasSiguiendo= new ArrayList<Periodista>();
			Collection<Periodista> periodistas = new ArrayList<Periodista>(this.periodistaService.findAll());

			periodistasSiguiendo=this.usuarioService.findByPrincipal().getPeriodistas();
			periodistas.removeAll(periodistasSiguiendo);

			result.addObject("periodistas", periodistas);
			result.addObject("message", message);
			result.addObject("periodistasSiguiendo", periodistasSiguiendo);

		} catch (Throwable oops) {
			oops.printStackTrace();
			System.out.println(oops.getMessage());
			result = super.forbiddenOpperation();
		}

		return result;
	}


	@RequestMapping(value = "/compartirInformacion", method = RequestMethod.POST)
	public ModelAndView compartirNoticia(@RequestParam int informacionId) {
		ModelAndView result;
		Informacion informacion;

		informacion= this.informacionService.findOne(informacionId);
		Assert.notNull(informacion);

		if(this.usuarioService.compartirInformacion(informacion)){

			result = this.createEditModelAndViewCompartirNoticia("usuario.compartirInformacion.exito");

		}else{

			result = this.createEditModelAndViewCompartirNoticia("usuario.compartirInformacion.error");
		}

		return result;
	}

	protected ModelAndView createEditModelAndViewCompartirNoticia(String message) {
		ModelAndView result;
		result = new ModelAndView("usuario/listNoticiasCompartidas");


		result.addObject("noticiasCompartidas", this.usuarioService.findByPrincipal().getInformacionCompartida());
		result.addObject("message", message);



		return result;
	}

	protected ModelAndView createEditModelAndViewApuntarseSorteo(String message,Integer puntos) {
		ModelAndView result;
		result = new ModelAndView("usuario/listSorteosProximos");


		result.addObject("listSorteosProximos", this.usuarioService.findSorteosProximos());

		result.addObject("message", message);

		result.addObject("puntos", puntos);
		return result;


	}


	@RequestMapping(value="/listSorteosProximos",method=RequestMethod.GET)
	public ModelAndView listSorteosProximos() {
		ModelAndView result;
		result = new ModelAndView("usuario/listSorteosProximos");


		result.addObject("listSorteosProximos", this.usuarioService.findSorteosProximos());
		return result;
	}

	@RequestMapping(value="/listTusSorteos",method=RequestMethod.GET)
	public ModelAndView listTusSorteos() {
		ModelAndView result;
		result = new ModelAndView("usuario/listTusSorteos");
		result.addObject("listTusSorteos", this.usuarioService.findByPrincipal().getSorteos());
		return result;
	}



	@RequestMapping(value = "/apuntarseSorteo", method = RequestMethod.POST)
	public ModelAndView apuntarseSorteo(@RequestParam int sorteoId) {
		ModelAndView result;
		Sorteo sorteo;
		sorteo= this.sorteoService.findOne(sorteoId);
		Assert.notNull(sorteo);
		if(this.usuarioService.apuntarseSorteo(sorteo)==2){

			result = this.createEditModelAndViewApuntarseSorteo("usuario.apuntarseSorteo.exito",null);

		}else if (this.usuarioService.apuntarseSorteo(sorteo)==1){
			Integer puntosRestantes=sorteo.getPuntosNecesarios()-this.usuarioService.findByPrincipal().getPuntos();
			result = this.createEditModelAndViewApuntarseSorteo("usuario.apuntarseSorteo.puntosInsuficientes",puntosRestantes);
		}else{
			result = this.createEditModelAndViewApuntarseSorteo("usuario.apuntarseSorteo.apuntandoPreviamente",null);
		}

		return result;
	}


	@RequestMapping(value="/cambiarEstatusMetodo",method = RequestMethod.POST)
	public ModelAndView cambiarEstatusMetodo() {
		ModelAndView result;
		Usuario usuarioLogued=this.usuarioService.findByPrincipal();
		Integer aux=null;
		aux=this.usuarioService.cambiarEstatus();


		if(aux==1){ //tiene puntos suficientes y cambia de estatus

			result = this.createEditModelAndViewcambiarEstatusMetodo("usuario.CambiarEstatus.exito",null);

		}else if(aux==2) { //no tenia puntos suficientes

			Tasa tasa= this.tasaService.getTasaRepository().findAll().get(0);
			Integer puntosRestantes;

			if(usuarioLogued.getEstatus().equals(Estatus.PRINCIPIANTE)){  //se calcula lo que te falta en caso de que seas principiante
				puntosRestantes=tasa.getCosteVeterano()-usuarioLogued.getPuntos();
				result = this.createEditModelAndViewcambiarEstatusMetodo("usuario.cambiarAVeterano.puntosInsuficientes",puntosRestantes);
			}
			else{ //se calcula lo que te falta en caso de que seas veterano
				puntosRestantes=tasa.getCosteMaestro()-usuarioLogued.getPuntos();
				result = this.createEditModelAndViewcambiarEstatusMetodo("usuario.cambiarAMaestro.puntosInsuficientes",puntosRestantes);
			}


		} else{
			result = this.createEditModelAndViewcambiarEstatusMetodo("usuario.CambiarEstatus.exito",null);
		}
		return result;
	}

	protected ModelAndView createEditModelAndViewcambiarEstatusMetodo(String message,Integer puntos) {
		ModelAndView result;
		result = new ModelAndView("usuario/cambiarEstatus");
		Usuario usuarioLogued=this.usuarioService.findByPrincipal();
		result.addObject("usuario",usuarioLogued);
		Estatus estatusMaestro= Estatus.MAESTRO;
		Estatus estatusVeterano= Estatus.VETERANO;
		Estatus estatusPrincipiante= Estatus.PRINCIPIANTE;
		Tasa tasa= this.tasaService.getTasaRepository().findAll().get(0);
		result.addObject("usuario",usuarioLogued);
		result.addObject("tasa",tasa);
		result.addObject("estatusMaestro",estatusMaestro);
		result.addObject("estatusVeterano",estatusVeterano);
		result.addObject("estatusPrincipiante",estatusPrincipiante);

		result.addObject("message", message);

		result.addObject("puntos", puntos);
		return result;


	}


	@RequestMapping(value="/cambiarEstatus",method=RequestMethod.GET)
	public ModelAndView cambiarEstatus() {
		ModelAndView result;
		result = new ModelAndView("usuario/cambiarEstatus");
		Usuario usuarioLogued=this.usuarioService.findByPrincipal();
		Estatus estatusMaestro= Estatus.MAESTRO;
		Estatus estatusVeterano= Estatus.VETERANO;
		Estatus estatusPrincipiante= Estatus.PRINCIPIANTE;
		Tasa tasa= this.tasaService.getTasaRepository().findAll().get(0);
		result.addObject("usuario",usuarioLogued);
		result.addObject("tasa",tasa);
		result.addObject("estatusMaestro",estatusMaestro);
		result.addObject("estatusVeterano",estatusVeterano);
		result.addObject("estatusPrincipiante",estatusPrincipiante);
		return result;
	}


	// List ------------------------
	@RequestMapping(value = "/listUsuariosToBan", method = RequestMethod.GET)
	public ModelAndView listToBan() {
		ModelAndView result;
		Collection<Usuario> usuariosBanned;
		Collection<Usuario> usuariosNotBanned;
		usuariosBanned = this.usuarioService.getUsuariosToBan();
		usuariosNotBanned = this.usuarioService.getUsuariosToUnBan();
		result = new ModelAndView("usuario/listUsuariosToBan");
		result.addObject("usuariosToBan", usuariosBanned);
		result.addObject("usuariosToUnban",usuariosNotBanned);
		result.addObject("requestURI", "usuario/listUsuariosToBan.do");
		return result;
	}

	@RequestMapping(value = "/listUsuariosToBan", method = RequestMethod.POST)
	public ModelAndView ban(@RequestParam int usuarioId) {
		ModelAndView result;
		this.usuarioService.saveBanUnban(usuarioId);
		result = this.listToBan();
		return result;
	}


	@RequestMapping(value = "/checkBan", method = RequestMethod.GET)
	public ModelAndView checkBan() {
		ModelAndView result;
		result = new ModelAndView("usuario/checkBan");
		return result;
	}

	@RequestMapping(value = "/checkBan", method = RequestMethod.POST )
	public ModelAndView checkBan2(@RequestParam("username") String username) {
		ModelAndView result;
		int banned=0;
		if(this.usuarioService.getUsuarioRepository().userAccountExist(username)>0)
			banned=2;
		else banned=1;

		result = new ModelAndView("usuario/checkBan");
		result.addObject("holder",username);
		result.addObject("banned",banned);


		return result;
	}



}
