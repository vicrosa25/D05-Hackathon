
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import domain.Periodista;
import domain.Usuario;
import forms.ModeradorForm;

@Service
@Transactional
public class ModeradorService {

	// Managed repository----------------------------------------------------------------------------------------------
	@Autowired
	private ModeradorRepository	moderadorRepository;

	// Supporting services --------------------------------------------------------------------------------------------
	@Autowired
	private UsuarioService		usuarioService;

	@Autowired
	private PeriodistaService	periodistaService;

	@Autowired
	private ActorService		actorService;

	// Validator
	@Autowired
	@Qualifier("validator")
	private Validator			validator;


	// Constructor
	public ModeradorService() {
		super();
	}

	// Simple SCRUD methods----------------------------------------------------------------------------------------------
	public Moderador create() {
		Moderador result = new Moderador();
		Assert.notNull(result);
		return result;
	}

	public Moderador save(Moderador moderador) {
		Assert.notNull(moderador);
		return this.moderadorRepository.save(moderador);
	}

	public void delete(Moderador moderador) {
		Assert.notNull(moderador);
		Assert.isTrue(this.findByPrincipal() == moderador);

		this.moderadorRepository.delete(moderador);
	}

	public Collection<Moderador> findAll() {
		return this.moderadorRepository.findAll();
	}

	public Moderador findOne(int id) {
		Moderador moderador = this.moderadorRepository.findOne(id);
		return moderador;
	}

	// check password
	public boolean isPasswordCorrect(String pass1, String pass2) {
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

		this.validator.validate(result, binding);

		return result;
	}

	// To edit
	public Moderador reconstruct(Moderador moderador, BindingResult binding) {
		Moderador result = moderador;

		// The pruned object is completed with the required fields
		this.registerOthersFields(result);

		this.validator.validate(result, binding);
		return result;
	}

	private void registerOthersFields(Moderador newOne) {
		Moderador oldOne = this.findByPrincipalToEdit();

		// Moderador
		newOne.setId(oldOne.getId());
		newOne.setUserAccount(oldOne.getUserAccount());
		newOne.getCartera().setSaldoAcumulado(oldOne.getCartera().getSaldoAcumulado());
		newOne.getCartera().setSaldoAcumuladoTotal(oldOne.getCartera().getSaldoAcumuladoTotal());

	}

	private Moderador findByPrincipalToEdit() {
		Moderador result = (Moderador) this.actorService.findByPrincipal();
		return result;
	}

	// Ban/Unban USUARIO -----------------------------------------------------------------------------------------
	public List<Usuario> getUsuariosToBan() {
		return this.usuarioService.getUsuariosToBan();

	}

	public List<Usuario> getUsuariosToUnBan() {
		return this.usuarioService.getUsuariosToUnBan();
	}

	public Usuario saveBanUnban(int id) {
		Authority authority = new Authority();
		authority.setAuthority(Authority.MODERADOR);
		Assert.notNull(this.usuarioService.findOne(id));
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(authority));

		Usuario toBanUnban = this.usuarioService.findOne(id);
		Boolean probe = toBanUnban.getBanned();

		if (toBanUnban.getBanned() == false) {
			toBanUnban.setUsernameCopyForBan(toBanUnban.getUserAccount().getUsername());
			toBanUnban.getUserAccount().setUsername(null);
			toBanUnban.setBanned(true);
		} else {
			toBanUnban.getUserAccount().setUsername(toBanUnban.getUsernameCopyForBan());
			toBanUnban.setBanned(false);
		}

		Usuario saved = this.usuarioService.save(toBanUnban);
		Assert.isTrue(!probe.equals(toBanUnban.getBanned()));
		return saved;
	}

	// Ban/Unban Periodista -----------------------------------------------------------------------------------------
	public Collection<Periodista> getPeriodistasToBan() {
		return this.periodistaService.findWithBannedNews();

	}

	public Collection<Periodista> getPeriodistasToUnBan() {
		return this.periodistaService.findBanned();
	}

	public Periodista saveBanUnbanPeriodista(int id) {
		Authority authority = new Authority();
		authority.setAuthority(Authority.MODERADOR);
		Assert.notNull(this.periodistaService.findOne(id));
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(authority));

		Periodista toBanUnban = this.periodistaService.findOne(id);
		Boolean probe = toBanUnban.getIsBanned();

		if (toBanUnban.getIsBanned() == false) {
			toBanUnban.setUsernameCopyForBan(toBanUnban.getUserAccount().getUsername());
			toBanUnban.getUserAccount().setUsername(null);
			toBanUnban.setIsBanned(true);
		} else {
			toBanUnban.getUserAccount().setUsername(toBanUnban.getUsernameCopyForBan());
			toBanUnban.setIsBanned(false);
		}

		Periodista saved = this.periodistaService.save(toBanUnban);
		Assert.isTrue(!probe.equals(toBanUnban.getIsBanned()));
		return saved;
	}

	public Moderador findByPrincipal() {
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
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(authority));
		Assert.isTrue(actual.getCartera().getSaldoAcumulado() >= 5.0);
		actual.getCartera().setSaldoAcumuladoTotal(actual.getCartera().getSaldoAcumulado() + actual.getCartera().getSaldoAcumuladoTotal());
		actual.getCartera().setSaldoAcumulado(0);
		Moderador saved = this.moderadorRepository.save(actual);
		Assert.isTrue(saved.getCartera().getSaldoAcumulado() == 0);
		return saved;
	}

}
