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

import domain.Periodista;
import security.LoginService;
import services.ActorService;
import services.PeriodistaService;
import utilities.Md5;

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


	// Display --------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		try{
			Periodista periodista = this.periodistaService.findByPrincipal();
			result = new ModelAndView("periodista/display");
			result.addObject("periodista", periodista);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
		return result;
	}

	// Display
	// --------------------------------------------------------------------------------------
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam int periodistaId) {
		ModelAndView result;
		try{
			result = new ModelAndView("periodista/display");
			Periodista periodista = this.periodistaService.findOne(periodistaId);
			result.addObject("periodista", periodista);

		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
		return result;
	}

	// Edit
	// --------------------------------------------------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		try{
			Periodista periodista = (Periodista) this.actorService.findByPrincipal();
			result = this.createEditModelAndView(periodista);
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}
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
	
	
	@RequestMapping(value = "/retirarDinero", method = RequestMethod.GET)
	public ModelAndView retirarDinero() {
		ModelAndView result;
		try{
			Periodista actual = this.periodistaService.getPeriodistaRepository()
				.findByUserAccountId(LoginService.getPrincipal().getId());
			Double dineroAcumulado = actual.getCartera().getSaldoAcumulado();
			Double dineroAcumuladoTotal = actual.getCartera()
				.getSaldoAcumuladoTotal();
			result = new ModelAndView("periodista/retirarDinero");
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

	@RequestMapping(value = "/retirarDinero", method = RequestMethod.POST)
	public ModelAndView retirar() {
		ModelAndView result;
		try{
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
		} catch (final Throwable oops) {
			System.out.println(oops.getMessage());
			System.out.println(oops.getClass());
			System.out.println(oops.getCause());
			oops.printStackTrace();
			result =super.forbiddenOpperation();
		}

		return result;
	}

	// List not full Agencias ------------------------------------------------------------------------------------
	@RequestMapping("/listPeriodista")
	public ModelAndView listNotFull() {
		ModelAndView result;
		try{
			Collection<Periodista> allPeriodista = this.periodistaService.findAll();

			result = new ModelAndView("periodista/listPeriodista");
			result.addObject("periodistas", allPeriodista);

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

	// Generate pdf ------------------------------------------------------------------------------------
	@RequestMapping(value = "/generatePDF")
	public void generatePDF(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Periodista periodista;

		try {
			final ServletContext servletContext = request.getSession().getServletContext();
			final File tempDirectory = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
			final String temperotyFilePath = tempDirectory.getAbsolutePath();
			periodista = this.periodistaService.findByPrincipal();

			String fileName = periodista.getNombre() + ".pdf";
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName);

			this.actorService.generatePersonalInformationPDF(periodista, temperotyFilePath + "\\" + fileName);
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
