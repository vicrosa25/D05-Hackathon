
package controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BuscadorService;
import services.UsuarioService;
import domain.Buscador;
import domain.Noticia;
import domain.Usuario;

@Controller
@RequestMapping("/buscador/usuario")
public class BuscadorController extends AbstractController {

	@Autowired
	private BuscadorService	buscadorService;

	@Autowired
	private UsuarioService	usuarioService;


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Clear -----------------------------------------------------------
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	public ModelAndView clear() {
		ModelAndView result;
		try {
			this.buscadorService.clear(this.usuarioService.findByPrincipal().getBuscador());
			result = new ModelAndView("redirect:/buscador/usuario/result.do");

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// Edit -----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		final Usuario usuario = this.usuarioService.findByPrincipal();
		final Buscador buscador = usuario.getBuscador();

		result = this.createEditModelAndView(buscador);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Buscador buscador, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final List<ObjectError> errors = binding.getAllErrors();
			for (final ObjectError e : errors)
				System.out.println(e.toString());
			result = this.createEditModelAndView(buscador);

		} else
			try {
				this.buscadorService.checkChanges(buscador);

				result = new ModelAndView("redirect:/buscador/usuario/result.do");
			} catch (final Throwable oops) {
				System.out.println(buscador);
				System.out.println(oops.getMessage());
				System.out.println(oops.getClass());
				System.out.println(oops.getCause());
				oops.printStackTrace();
				result = this.createEditModelAndView(buscador);
			}
		return result;
	}

	// Search -----------------------------------------------------------
	@RequestMapping(value = "/result", method = RequestMethod.GET)
	public ModelAndView result() {
		ModelAndView result;

		try {
			final Usuario usuario = this.usuarioService.findByPrincipal();
			final Buscador buscador = usuario.getBuscador();
			final Collection<Noticia> noticias = this.buscadorService.getResults(buscador);

			result = new ModelAndView("buscador/usuario/result");
			result.addObject("noticias", noticias);
			result.addObject("requestURI", "buscador/usuario/result.do");
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Buscador buscador) {
		ModelAndView result;

		result = this.createEditModelAndView(buscador, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Buscador buscador, final String message) {
		ModelAndView result;

		result = new ModelAndView("buscador/usuario/edit");
		result.addObject("buscador", buscador);
		result.addObject("message", message);

		return result;
	}
}
