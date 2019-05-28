package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Moderador;
import domain.Periodista;
import domain.Usuario;
import forms.ModeradorForm;
import forms.PeriodistaForm;
import forms.UsuarioForm;
import services.ModeradorService;
import services.PeriodistaService;
import services.UsuarioService;
import utilities.Md5;

@Controller
@RequestMapping("/registration")
public class RegistrationController extends AbstractController {

	@Autowired
	private UsuarioService 		usuarioService;

	@Autowired
	private PeriodistaService 	periodistaService;

	@Autowired
	private ModeradorService 	moderadorService;

	// Constructor
	public RegistrationController() {
		super();
	}

	// Registration as an Usuario
	// --------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/usuario", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView result;
		UsuarioForm usuarioForm = new UsuarioForm();
		Assert.notNull(usuarioForm);
		result = new ModelAndView("registration/usuario");
		result.addObject("usuarioForm", usuarioForm);
		return result;
	}

	@RequestMapping(value = "/usuario", method = RequestMethod.POST, params = "register")
	public ModelAndView registerAndSave(UsuarioForm usuarioForm, BindingResult binding) {
		ModelAndView result = new ModelAndView("registration/usuario");
		String password;
		Usuario usuario;
		String pass1 = usuarioForm.getUserAccount().getPassword();
		String pass2 = usuarioForm.getPasswordCheck();

		if (!usuarioForm.isAccepted()) {
			result = new ModelAndView("registration/usuario");
			result.addObject("usuarioForm", usuarioForm);
			result.addObject("message", "user.accepted");
		} else {
			usuario = this.usuarioService.reconstruc(usuarioForm, binding);

			if (!this.usuarioService.isPasswordCorrect(pass1, pass2)) {
				result = new ModelAndView("registration/usuario");
				result.addObject("usuarioForm", usuarioForm);
				result.addObject("message", "user.pass.incorrect");
			} else {
				if (binding.hasErrors()) {
					List<ObjectError> errors = binding.getAllErrors();
					for (ObjectError e : errors) {
						System.out.println(e.toString());
					}
					result = new ModelAndView("registration/usuario");
					result.addObject("usuario", usuario);
				} else {
					try {
						password = Md5.encodeMd5(usuario.getUserAccount().getPassword());
						usuario.getUserAccount().setPassword(password);
						this.usuarioService.save(usuario);
						result = new ModelAndView("redirect:../security/login.do");
					} catch (Throwable oops) {
						result = new ModelAndView("registration/usuario");
						result.addObject("usuario", usuario);
						if (oops instanceof DataIntegrityViolationException) {
							result.addObject("message", "user.duplicated");
						} else {
							result.addObject("message", oops.getMessage());
						}
					}
				}
			}
		}
		return result;
	}

	// Registration as a Periodista
	// --------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/periodista", method = RequestMethod.GET)
	public ModelAndView registerAsPeriodista() {
		ModelAndView result;
		PeriodistaForm periodistaForm = new PeriodistaForm();
		Assert.notNull(periodistaForm);
		result = new ModelAndView("registration/periodista");
		result.addObject("periodistaForm", periodistaForm);
		return result;
	}

	@RequestMapping(value = "/periodista", method = RequestMethod.POST, params = "register")
	public ModelAndView registerAndSavePeriodista(PeriodistaForm periodistaForm, BindingResult binding) {
		ModelAndView result = new ModelAndView("registration/periodista");
		String password;
		Periodista periodista;
		String pass1 = periodistaForm.getUserAccount().getPassword();
		String pass2 = periodistaForm.getPasswordCheck();

		if (!periodistaForm.isAccepted()) {
			result = new ModelAndView("registration/periodista");
			result.addObject("periodistaForm", periodistaForm);
			result.addObject("message", "periodista.accepted");
		} else {
			periodista = this.periodistaService.reconstruc(periodistaForm, binding);

			if (!this.usuarioService.isPasswordCorrect(pass1, pass2)) {
				result = new ModelAndView("registration/periodista");
				result.addObject("periodistaForm", periodistaForm);
				result.addObject("message", "periodista.pass.incorrect");
			} else {
				if (binding.hasErrors()) {
					List<ObjectError> errors = binding.getAllErrors();
					for (ObjectError e : errors) {
						System.out.println(e.toString());
					}
					result = new ModelAndView("registration/periodista");
					result.addObject("periodista", periodista);
				} else {
					try {
						password = Md5.encodeMd5(periodista.getUserAccount().getPassword());
						periodista.getUserAccount().setPassword(password);
						this.periodistaService.save(periodista);
						result = new ModelAndView("redirect:../security/login.do");
					} catch (Throwable oops) {
						result = new ModelAndView("registration/periodista");
						result.addObject("periodista", periodista);
						if (oops instanceof DataIntegrityViolationException) {
							result.addObject("message", "periodista.duplicated");
						}
					}
				}
			}
		}
		return result;
	}

	// Registration as a Moderador
	// ---------------------------------------------------------------------------------
	@RequestMapping(value = "/moderador", method = RequestMethod.GET)
	public ModelAndView registerAsModerador() {
		ModelAndView result;
		ModeradorForm moderadorForm = new ModeradorForm();
		Assert.notNull(moderadorForm);
		result = new ModelAndView("registration/moderador");
		result.addObject("moderadorForm", moderadorForm);
		return result;
	}

	@RequestMapping(value = "/moderador", method = RequestMethod.POST, params = "register")
	public ModelAndView registerAndSaveAsModerador(ModeradorForm moderadorForm, BindingResult binding) {
		ModelAndView result = new ModelAndView("registration/moderador");
		String password;
		Moderador moderador;
		String pass1 = moderadorForm.getUserAccount().getPassword();
		String pass2 = moderadorForm.getPasswordCheck();

		if (!moderadorForm.isAccepted()) {
			result = new ModelAndView("registration/moderador");
			result.addObject("moderadorForm", moderadorForm);
			result.addObject("message", "user.accepted");
		} else {
			moderador = this.moderadorService.reconstruc(moderadorForm, binding);

			if (!this.moderadorService.isPasswordCorrect(pass1, pass2)) {
				result = new ModelAndView("registration/moderador");
				result.addObject("moderadorForm", moderadorForm);
				result.addObject("message", "user.pass.incorrect");
			} else {
				if (binding.hasErrors()) {
					List<ObjectError> errors = binding.getAllErrors();
					for (ObjectError e : errors) {
						System.out.println(e.toString());
					}
					result = new ModelAndView("registration/moderador");
					result.addObject("moderador", moderador);
				} else {
					try {
						password = Md5.encodeMd5(moderador.getUserAccount().getPassword());
						moderador.getUserAccount().setPassword(password);
						this.moderadorService.save(moderador);
						result = new ModelAndView("redirect:../security/login.do");
					} catch (Throwable oops) {
						result = new ModelAndView("registration/moderador");
						result.addObject("moderador", moderador);
						if (oops instanceof DataIntegrityViolationException) {
							result.addObject("message", "periodista.duplicated");
						} else {
							result.addObject("message", oops.getMessage());
						}
					}
				}
			}
		}
		return result;
	}

}