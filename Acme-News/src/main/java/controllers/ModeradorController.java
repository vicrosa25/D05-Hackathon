
package controllers;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ModeradorService;
import domain.Moderador;
import domain.Periodista;
import domain.Usuario;
import services.ActorService;
import services.ModeradorService;
import utilities.Md5;

@Controller
@RequestMapping("/moderador")
public class ModeradorController extends AbstractController {

	@Autowired
	private ModeradorService	moderadorService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ActorService actorService;


	// Constructor
	public ModeradorController() {
		super();
	}

	// List ------------------------
	@RequestMapping(value = "/retirarDinero", method = RequestMethod.GET)
	public ModelAndView retirarDinero() {
		ModelAndView result;
		Moderador actual;
		try{

			actual = this.moderadorService.findByPrincipal();
			Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();
			Double dineroAcumuladoTotal = actual.getCartera().getSaldoAcumuladoTotal();

			result = new ModelAndView("moderador/retirarDinero");
			result.addObject("dineroAcumulado", dineroAcumulado);
			result.addObject("dineroAcumuladoTotal", dineroAcumuladoTotal);
			result.addObject("error", 0);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
		return result;
	}

	// Edit --------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Moderador moderador;
		
		moderador = (Moderador) this.actorService.findByPrincipal();
		result = this.createEditModelAndView(moderador);
		return result;
	}

	// Save --------------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Moderador moderador, BindingResult binding) {
		ModelAndView result;
		String password;

		moderador = this.moderadorService.reconstruct(moderador, binding);
		if (binding.hasErrors()) {
			List<ObjectError> errors = binding.getAllErrors();
			for (ObjectError e : errors) {
				System.out.println(e.toString());
			}
			result = this.createEditModelAndView(moderador);
		} else {
			try {
				password = Md5.encodeMd5(moderador.getUserAccount().getPassword());
				moderador.getUserAccount().setPassword(password);
				this.moderadorService.save(moderador);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(moderador, "moderador.duplicated");
			}
		}

		return result;
	}

	// Ban/Uban USUARIOS -------------------------------------------------------------------------------------
	@RequestMapping(value = "/usuarios/ban", method = RequestMethod.GET)
	public ModelAndView listToBan() {
		ModelAndView result;
		Collection<Usuario> usuariosBanned;
		Collection<Usuario> usuariosNotBanned;
		try{
			usuariosBanned = this.moderadorService.getUsuariosToBan();
			usuariosNotBanned = this.moderadorService.getUsuariosToUnBan();

			result = new ModelAndView("moderador/usuarios/ban");
			result.addObject("usuariosToBan", usuariosBanned);
			result.addObject("usuariosToUnban", usuariosNotBanned);
			result.addObject("requestURI", "moderador/usuarios/ban.do");
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/usuarios/ban", method = RequestMethod.POST)
	public ModelAndView ban(@RequestParam int usuarioId) {
		ModelAndView result;
		try{
			this.moderadorService.saveBanUnban(usuarioId);
			result = this.listToBan();
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
		return result;
	}

	// Ban/Uban PERIODISTAS -------------------------------------------------------------------------------------
	@RequestMapping(value = "/periodistas/ban", method = RequestMethod.GET)
	public ModelAndView peridistasToBan() {
		ModelAndView result;
		Collection<Periodista> periodistasToBan;
		Collection<Periodista> periodistasToUnban;
		try{
			periodistasToBan = this.moderadorService.getPeriodistasToBan();
			periodistasToUnban = this.moderadorService.getPeriodistasToUnBan();

			result = new ModelAndView("moderador/periodistas/ban");
			result.addObject("periodistasToBan", periodistasToBan);
			result.addObject("periodistasToUnban", periodistasToUnban);
			result.addObject("requestURI", "moderador/periodistas/ban.do");
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}

		return result;
	}

	@RequestMapping(value = "/periodistas/ban", method = RequestMethod.POST)
	public ModelAndView periodistasToBan(@RequestParam int periodistaId) {
		ModelAndView result;
		try{
			this.moderadorService.saveBanUnbanPeriodista(periodistaId);
			result = this.peridistasToBan();
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
		return result;
	}

	// Retirar Dinero ---------------------------------------------------------------------------------------------
	@RequestMapping(value = "/retirarDinero", method = RequestMethod.POST)
	public ModelAndView retirar() {
		ModelAndView result;
		Moderador actual;
		try{
			actual = this.moderadorService.findByPrincipal();
			Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();

			if (dineroAcumulado >= 5.0) {
				this.moderadorService.retirarDinero();
				result = this.retirarDinero();
			} else {
				int error = 1;
				result = this.retirarDinero();
				result.addObject("error", error);
			}

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
		return result;
	}
	// Other methods-------------------------------------------------------------------------------------------------------
	private ModelAndView createEditModelAndView(Moderador moderador) {
		ModelAndView result;
		result = this.createEditModelAndView(moderador, null);
		return result;
	}

	private ModelAndView createEditModelAndView(Moderador moderador, String message) {
		ModelAndView result;
		result = new ModelAndView("moderador/edit");
		result.addObject("action", "moderador/edit.do");
		result.addObject("modelAttribute", "moderador");
		result.addObject("moderador", moderador);
		result.addObject("message", message);
		return result;
	}

	// Generate pdf ------------------------------------------------------------------------------------
	@RequestMapping(value = "/generatePDF")
	public void generatePDF(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Moderador moderador;

		try {
			final ServletContext servletContext = request.getSession().getServletContext();
			final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			final String temperotyFilePath = tempDirectory.getAbsolutePath();
			moderador = this.moderadorService.findByPrincipal();

			String fileName = moderador.getNombre() + ".pdf";
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);

			this.actorService.generatePersonalInformationPDF(moderador, temperotyFilePath + "\\" + fileName);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos = this.convertPDFToByteArrayOutputStream(temperotyFilePath + "\\" + fileName);
			OutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
		}
	}
}
