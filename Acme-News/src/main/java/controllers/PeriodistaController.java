package controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ActorService;
import services.PeriodistaService;
import utilities.Md5;
import domain.Periodista;

@Controller
@RequestMapping("/periodista")
public class PeriodistaController extends AbstractController {

	// Services
	@Autowired
	private PeriodistaService periodistaService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------
	public PeriodistaController() {
		super();
	}


	@ExceptionHandler(TypeMismatchException.class)
	public ModelAndView handleMismatchException(final TypeMismatchException oops) {
		return new ModelAndView("redirect:/");
	}

	// Display
	// --------------------------------------------------------------------------------------
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam int periodistaId) {
		ModelAndView result;

		result = new ModelAndView("periodista/display");
		Periodista periodista = this.periodistaService.findOne(periodistaId);
		result.addObject("periodista", periodista);

		return result;
	}

	// Edit
	// --------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Periodista periodista = (Periodista) this.actorService.findByPrincipal();
		result = this.createEditModelAndView(periodista);
		return result;
	}

	// Save
	// --------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(Periodista periodista, BindingResult binding) {
		ModelAndView result;
		String password;

		periodista = this.periodistaService.reconstruct(periodista, binding);
		if (binding.hasErrors()) {
			List<ObjectError> errors = binding.getAllErrors();
			for (ObjectError e : errors) {
				System.out.println(e.toString());
			}
			result = this.createEditModelAndView(periodista);
		} else {
			try {
				password = Md5.encodeMd5(periodista.getUserAccount()
					.getPassword());
				periodista.getUserAccount().setPassword(password);
				this.periodistaService.save(periodista);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(periodista,
					"periodista.duplicated");
			}
		}

		return result;
	}

	// Other
	// methods-------------------------------------------------------------------------------------------------------
	private ModelAndView createEditModelAndView(Periodista periodista) {
		ModelAndView result;
		result = this.createEditModelAndView(periodista, null);
		return result;
	}

	private ModelAndView createEditModelAndView(Periodista periodista,
		String message) {
		ModelAndView result;
		result = new ModelAndView("periodista/edit");
		result.addObject("action", "periodista/edit.do");
		result.addObject("modelAttribute", "periodista");
		result.addObject("periodista", periodista);
		result.addObject("message", message);
		return result;
	}

	@RequestMapping(value = "/retirarDinero", method = RequestMethod.GET)
	public ModelAndView retirarDinero() {
		ModelAndView result;
		Periodista actual = this.periodistaService.getPeriodistaRepository()
			.findByUserAccountId(LoginService.getPrincipal().getId());
		Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();
		Double dineroAcumuladoTotal = actual.getCartera()
			.getSaldoAcumuladoTotal();
		result = new ModelAndView("periodista/retirarDinero");
		result.addObject("dineroAcumulado", dineroAcumulado);
		result.addObject("dineroAcumuladoTotal", dineroAcumuladoTotal);
		result.addObject("error", 0);
		return result;
	}

	@RequestMapping(value = "/retirarDinero", method = RequestMethod.POST)
	public ModelAndView retirar() {
		ModelAndView result;
		Periodista actual = this.periodistaService.getPeriodistaRepository()
			.findByUserAccountId(LoginService.getPrincipal().getId());
		Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();
		if (dineroAcumulado >= 5.0) {
			this.periodistaService.retirarDinero();
			result = this.retirarDinero();
		} else {
			int error = 1;
			result = this.retirarDinero();
			result.addObject("error", error);
		}

		return result;
	}

	// List not full Agencias
	@RequestMapping("/listPeriodista")
	public ModelAndView listNotFull() {
		Collection<Periodista> allPeriodista = this.periodistaService.findAll();

		ModelAndView result = new ModelAndView("periodista/listPeriodista");
		result.addObject("periodistas", allPeriodista);

		return result;
	}

}
