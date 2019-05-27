
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Moderador;
import domain.Periodista;
import domain.Usuario;
import services.ModeradorService;

@Controller
@RequestMapping("/moderador")
public class ModeradorController extends AbstractController {

	@Autowired
	private ModeradorService moderadorService;


	// Constructor
	public ModeradorController() {
		super();
	}

	// List ------------------------
	@RequestMapping(value = "/retirarDinero", method = RequestMethod.GET)
	public ModelAndView retirarDinero() {
		ModelAndView result;
		Moderador actual;

		actual = this.moderadorService.findByPrincipal();
		Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();
		Double dineroAcumuladoTotal = actual.getCartera().getSaldoAcumuladoTotal();

		result = new ModelAndView("moderador/retirarDinero");
		result.addObject("dineroAcumulado", dineroAcumulado);
		result.addObject("dineroAcumuladoTotal", dineroAcumuladoTotal);
		result.addObject("error", 0);
		return result;
	}

	// Ban/Uban USUARIOS -------------------------------------------------------------------------------------
	@RequestMapping(value = "/usuarios/ban", method = RequestMethod.GET)
	public ModelAndView listToBan() {
		ModelAndView result;
		Collection<Usuario> usuariosBanned;
		Collection<Usuario> usuariosNotBanned;

		usuariosBanned = this.moderadorService.getUsuariosToBan();
		usuariosNotBanned = this.moderadorService.getUsuariosToUnBan();

		result = new ModelAndView("moderador/usuarios/ban");
		result.addObject("usuariosToBan", usuariosBanned);
		result.addObject("usuariosToUnban", usuariosNotBanned);
		result.addObject("requestURI", "moderador/usuarios/ban.do");

		return result;
	}

	@RequestMapping(value = "/usuarios/ban", method = RequestMethod.POST)
	public ModelAndView ban(@RequestParam int usuarioId) {
		ModelAndView result;
		this.moderadorService.saveBanUnban(usuarioId);
		result = this.listToBan();
		return result;
	}

	// Ban/Uban PERIODISTAS -------------------------------------------------------------------------------------
	@RequestMapping(value = "/periodistas/ban", method = RequestMethod.GET)
	public ModelAndView peridistasToBan() {
		ModelAndView result;
		Collection<Periodista> periodistasToBan;
		Collection<Periodista> periodistasToUnban;

		periodistasToBan = this.moderadorService.getPeriodistasToBan();
		periodistasToUnban = this.moderadorService.getPeriodistasToUnBan();

		result = new ModelAndView("moderador/periodistas/ban");
		result.addObject("periodistasToBan", periodistasToBan);
		result.addObject("periodistasToUnban", periodistasToUnban);
		result.addObject("requestURI", "moderador/periodistas/ban.do");

		return result;
	}

	@RequestMapping(value = "/periodistas/ban", method = RequestMethod.POST)
	public ModelAndView periodistasToBan(@RequestParam int periodistaId) {
		ModelAndView result;
		this.moderadorService.saveBanUnbanPeriodista(periodistaId);
		result = this.peridistasToBan();
		return result;
	}
	
	

	// Retirar Dinero ---------------------------------------------------------------------------------------------
	@RequestMapping(value = "/retirarDinero", method = RequestMethod.POST)
	public ModelAndView retirar() {
		ModelAndView result;
		Moderador actual;

		actual = this.moderadorService.findByPrincipal();
		Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();

		if (dineroAcumulado >= 5.0) {
			moderadorService.retirarDinero();
			result = this.retirarDinero();
		} else {
			int error = 1;
			result = this.retirarDinero();
			result.addObject("error", error);
		}

		return result;
	}

}
