/*
 * WelcomeController.java
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
import domain.Banner;
import services.ActorService;
import services.BannerService;
import services.ConfigurationsService;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	

	@Autowired
	private ActorService			actorService;
	
	@Autowired
	private ConfigurationsService	configurationsService;
	
	@Autowired
	private BannerService bannerService;

	
	
	// Constructors -----------------------------------------------------------
	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(@CookieValue(value = "language", defaultValue = "") String language) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		String name;
		Actor actor;
		String englishMessage;
		String spanishMessage;

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		
		Banner  banner = this.bannerService.getBannerWellcome();
		
		englishMessage = this.configurationsService.getConfiguration().getEnglishMessage();
		spanishMessage = this.configurationsService.getConfiguration().getSpanishMessage();
		

		List<String> urls= new ArrayList<String>(banner.getImagenes());
		Integer aleatorio=(int) (Math.random() * urls.size()); // numero aleatorio generado entre 0 y el numero de urls
		String bannerAleatorio = urls.get(aleatorio);
		
		
		
		try {
			actor = this.actorService.findByPrincipal();
			name = actor.getNombre() + " " + actor.getApellidos();
			result = new ModelAndView("welcome/index");
			result.addObject("moment", moment);
			result.addObject("name", name);
			result.addObject("englishMessage", englishMessage);
			result.addObject("spanishMessage", spanishMessage);
			result.addObject("language", language);
			result.addObject("bannerAleatorio", bannerAleatorio);
			return result;
		} catch (final Exception e) {
			result = new ModelAndView("welcome/index");
			result.addObject("moment", moment);
			result.addObject("englishMessage", englishMessage);
			result.addObject("spanishMessage", spanishMessage);
			result.addObject("language", language);
			result.addObject("bannerAleatorio", bannerAleatorio);
		}

		return result;
	}
}
