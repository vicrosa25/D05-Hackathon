package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import domain.Moderador;
import security.LoginService;
import services.ModeradorService;




@Controller
@RequestMapping("/moderador")
public class ModeradorController extends AbstractController {
		
	
		@Autowired
		private ModeradorService moderadorService;

		
		// Constructor
		public ModeradorController(){
			super();
		}
		
		
	
		
		// List ------------------------
		@RequestMapping(value = "/retirarDinero", method = RequestMethod.GET)
		public ModelAndView retirarDinero() {
			ModelAndView result;
			Moderador actual= this.moderadorService.getModeradorRepository().findByUserAccountId(LoginService.getPrincipal().getId());
			Double dineroAcumulado= actual.getCartera().getSaldoAcumulado();
			Double dineroAcumuladoTotal= actual.getCartera().getSaldoAcumuladoTotal();
			result = new ModelAndView("moderador/retirarDinero");
			result.addObject("dineroAcumulado", dineroAcumulado);
			result.addObject("dineroAcumuladoTotal",dineroAcumuladoTotal);
			result.addObject("error",0);
			return result;
		}
		
		@RequestMapping(value = "/retirarDinero", method = RequestMethod.POST)
		public ModelAndView retirar() {
			ModelAndView result;
			Moderador actual= this.moderadorService.getModeradorRepository().findByUserAccountId(LoginService.getPrincipal().getId());
			Double dineroAcumulado= actual.getCartera().getSaldoAcumulado();
			if(dineroAcumulado>=5.0){
			moderadorService.retirarDinero();
			result = this.retirarDinero();
			}
			else{
				int error=1;
				result = this.retirarDinero();
				result.addObject("error",error);
			}
			
			return result;
		}
		
		
	
}