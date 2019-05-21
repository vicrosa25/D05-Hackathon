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
import domain.Informacion;
import domain.Periodista;
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
		this.periodistaRepository.delete(periodista);
	}
	
	public Collection<Periodista> findAll(){
		return this.periodistaRepository.findAll();
	}
	
	public Periodista findOne(int id){
		Periodista periodista = this.periodistaRepository.findOne(id);
		Assert.notNull(periodista);
		return periodista;
	}
	
	
	// check password
	public boolean isPasswordCorrect(String pass1, String pass2){
		return pass1.equals(pass2);
	}
	
	// Reconstruct to UsuarioForm
	public Periodista reconstruc(PeriodistaForm periodistaForm, BindingResult binding) {
		System.out.println(periodistaForm);
		Periodista result = this.create();
		// UserAccount
		Authority authority = new Authority();
		authority.setAuthority(Authority.PERIODISTA);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		
		result.setUserAccount(periodistaForm.getUserAccount());
		result.getUserAccount().setAuthorities(authorities);
		
		
		// Informacion
		Collection<Informacion> informacion = new ArrayList<Informacion>();
		result.setInformacion(informacion);
		
		// Cartera
		periodistaForm.getCartera().setSaldoAcumulado(0);
		periodistaForm.getCartera().setSaldoAcumuladoTotal(0);
		result.setCartera(periodistaForm.getCartera());
		
		// Periodista
		result.setNombre(periodistaForm.getNombre());
		result.setApellidos(periodistaForm.getApellidos());
		result.setEmail(periodistaForm.getEmail());
		
		validator.validate(result, binding);

		return result;
	}
	
	
	// To edit a usuario's profile
	public Periodista reconstruct(Periodista periodista, BindingResult binding) {	
		Periodista result = periodista;

		// The pruned object is completed with the required fields
		registerOthersFields(result);

		validator.validate(result, binding);
		return result;
	}

	private void registerOthersFields(Periodista newOne) {
		Periodista oldOne = findByPrincipalToEdit();
		
		// Periodista
		newOne.setId(oldOne.getId());
		newOne.setUserAccount(oldOne.getUserAccount());
		newOne.setAgencia(oldOne.getAgencia());
		newOne.setInformacion(oldOne.getInformacion());
		newOne.getCartera().setSaldoAcumulado(oldOne.getCartera().getSaldoAcumulado());
		newOne.getCartera().setSaldoAcumuladoTotal(oldOne.getCartera().getSaldoAcumuladoTotal());
		
	}

	private Periodista findByPrincipalToEdit() {
		Periodista result = (Periodista) actorService.findByPrincipal();
		return result;
	}
	
	public PeriodistaRepository getPeriodistaRepository() {
		return periodistaRepository;
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