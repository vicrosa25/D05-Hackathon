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

import repositories.ModeradorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Moderador;
import forms.ModeradorForm;

@Service
@Transactional
public class ModeradorService {
	
	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private ModeradorRepository moderadorRepository;
	
	
	// Validator
	@Autowired
	@Qualifier("validator")
	private Validator validator;
	
	
	// Constructor
	public ModeradorService(){
		super();
	}
	
	
	// Simple SCRUD methods----------------------------------------------------------------------------------------------
	public Moderador create(){
		Moderador result = new Moderador();
		Assert.notNull(result);
		return result;
	}
	
	public Moderador save(Moderador moderador){
		Assert.notNull(moderador);
		return this.moderadorRepository.save(moderador);
	}
	
	public void delete(Moderador moderador){
		this.moderadorRepository.delete(moderador);
	}
	
	public Collection<Moderador> findAll(){
		return this.moderadorRepository.findAll();
	}
	
	public Moderador findOne(int id){
		Moderador moderador = this.moderadorRepository.findOne(id);
		return moderador;
	}
	
	
	// check password
	public boolean isPasswordCorrect(String pass1, String pass2){
		return pass1.equals(pass2);
	}
	
	// Reconstruct to moderadorForm
	public Moderador reconstruc(ModeradorForm moderadorForm, BindingResult binding) {
		Moderador result = this.create();
		// UserAccount
		Authority authority = new Authority();
		authority.setAuthority(Authority.MODERADOR);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		
		result.setUserAccount(moderadorForm.getUserAccount());
		result.getUserAccount().setAuthorities(authorities);
		
		// Cartera
		moderadorForm.getCartera().setSaldoAcumulado(0);
		moderadorForm.getCartera().setSaldoAcumuladoTotal(0);
		result.setCartera(moderadorForm.getCartera());
		
		// Usuario
		result.setNombre(moderadorForm.getNombre());
		result.setApellidos(moderadorForm.getApellidos());
		result.setEmail(moderadorForm.getEmail());
		
		validator.validate(result, binding);

		return result;
	}
	
	public ModeradorRepository getModeradorRepository() {
		return moderadorRepository;
	}
	
	public Moderador findByPrincipal(){
		Moderador result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		result = this.moderadorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);
		return result;
	}
	
	public Moderador retirarDinero() {
		Moderador actual = this.moderadorRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		Authority authority = new Authority();
		authority.setAuthority(Authority.MODERADOR);
		Assert.notNull(actual);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities()
				.contains(authority));
		Assert.isTrue(actual.getCartera().getSaldoAcumulado()>=5.0);
		actual.getCartera().setSaldoAcumuladoTotal(actual.getCartera().getSaldoAcumulado()+actual.getCartera().getSaldoAcumuladoTotal());
		actual.getCartera().setSaldoAcumulado(0);
		Moderador saved = this.moderadorRepository.save(actual);
		Assert.isTrue(saved.getCartera().getSaldoAcumulado()==0);
		return saved;
	}


}