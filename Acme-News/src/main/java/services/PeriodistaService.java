package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PeriodistaRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Noticia;
import domain.Periodista;
import domain.Usuario;
import forms.PeriodistaForm;

@Service
@Transactional
public class PeriodistaService {


	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private PeriodistaRepository periodistaRepository;


	// Supporting services ----------------------------------------------------------------------------------------------
	@Autowired
	private ActorService actorService;


	// Validator
	@Autowired
	@Qualifier("validator")
	private Validator validator;


	// Constructor
	public PeriodistaService(){
		super();
	}




	// Simple SCRUD methods----------------------------------------------------------------------------------------------
	public Periodista create(){
		Periodista result = new Periodista();
		System.out.println(result);
		return result;
	}


	public Periodista save(Periodista periodista){
		Assert.notNull(periodista);
		return this.periodistaRepository.save(periodista);
	}

	public void delete(Periodista periodista){
		Assert.notNull(periodista);
		Assert.isTrue(this.findByPrincipal() == periodista);

		Periodista unknown = this.findUnknown();

		if(periodista.getAgencia() != null)
			periodista.getAgencia().getPeriodistas().remove(periodista);
		for(Noticia noticia:periodista.getNoticias()){
			noticia.setPeriodista(unknown);
		}
		for(Usuario user:this.periodistaRepository.findFollowers(periodista.getId())){
			user.getPeriodistas().remove(periodista);
		}

		this.periodistaRepository.delete(periodista);
	}

	public Periodista findUnknown(){
		Periodista result = (Periodista) this.actorService.findByUsername("UnknownJournalist");
		Assert.notNull(result, "No se encuentra el periodista 'desconocido', hace falta resetear la BDD para eliminar periodistas");
		return result;
	}

	public Collection<Periodista> findAll(){
		return this.periodistaRepository.findAll();
	}

	public Periodista findOne(int id){
		Periodista periodista = this.periodistaRepository.findOne(id);
		Assert.notNull(periodista);
		return periodista;
	}


	public Collection<Periodista> findWithBannedNews() {
		Collection<Periodista> result = new ArrayList<Periodista>();
		Collection<Periodista> periodistas = this.periodistaRepository.findWithBannedNew();

		for (Periodista periodista : periodistas) {
			if (periodista.getIsBanned() == false) {
				result.add(periodista);
			}
		}

		return result;
	}


	public Collection<Periodista> findBanned() {
		return this.getPeriodistaRepository().findBanned();
	}


	// check password
	public boolean isPasswordCorrect(String pass1, String pass2){
		return pass1.equals(pass2);
	}

	// Reconstruct to PeriodistaForm
	public Periodista reconstruc(PeriodistaForm periodistaForm, BindingResult binding) {

		Periodista result = this.create();
		// UserAccount
		Authority authority = new Authority();
		authority.setAuthority(Authority.PERIODISTA);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);

		result.setUserAccount(periodistaForm.getUserAccount());
		result.getUserAccount().setAuthorities(authorities);


		// Informacion
		Collection<Noticia> noticias = new ArrayList<Noticia>();
		result.setNoticias(noticias);

		// Cartera
		periodistaForm.getCartera().setSaldoAcumulado(0);
		periodistaForm.getCartera().setSaldoAcumuladoTotal(0);
		result.setCartera(periodistaForm.getCartera());

		// Periodista
		result.setNombre(periodistaForm.getNombre());
		result.setApellidos(periodistaForm.getApellidos());
		result.setEmail(periodistaForm.getEmail());


		this.validator.validate(result, binding);

		return result;
	}


	// To edit
	public Periodista reconstruct(Periodista periodista, BindingResult binding) {
		Periodista result = periodista;

		// The pruned object is completed with the required fields
		this.registerOthersFields(result);

		this.validator.validate(result, binding);
		return result;
	}

	private void registerOthersFields(Periodista newOne) {
		Periodista oldOne = this.findByPrincipalToEdit();

		// Periodista
		newOne.setId(oldOne.getId());
		newOne.setUserAccount(oldOne.getUserAccount());
		newOne.setAgencia(oldOne.getAgencia());
		newOne.setNoticias(oldOne.getNoticias());
		newOne.getCartera().setSaldoAcumulado(oldOne.getCartera().getSaldoAcumulado());
		newOne.getCartera().setSaldoAcumuladoTotal(oldOne.getCartera().getSaldoAcumuladoTotal());

	}

	private Periodista findByPrincipalToEdit() {
		Periodista result = (Periodista) this.actorService.findByPrincipal();
		return result;
	}

	public PeriodistaRepository getPeriodistaRepository() {
		return this.periodistaRepository;
	}

	public Periodista findByPrincipal(){
		Periodista result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		result = this.periodistaRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		return result;
	}

	public Boolean checkAgencia(){
		Boolean res = false;
		Periodista p = this.findByPrincipal();
		if(p.getAgencia() != null){
			res=true;
		}
		return res;
	}

	public Periodista retirarDinero() {
		Periodista actual = this.periodistaRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		Authority authority = new Authority();
		authority.setAuthority(Authority.PERIODISTA);
		Assert.notNull(actual);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities()
			.contains(authority));
		Assert.isTrue(actual.getCartera().getSaldoAcumulado()>=5.0);

		actual.getCartera().setSaldoAcumuladoTotal(actual.getCartera().getSaldoAcumulado()+actual.getCartera().getSaldoAcumuladoTotal());
		actual.getCartera().setSaldoAcumulado(0);
		Periodista saved = this.periodistaRepository.save(actual);
		Assert.isTrue(saved.getCartera().getSaldoAcumulado()==0);
		return saved;
	}
}