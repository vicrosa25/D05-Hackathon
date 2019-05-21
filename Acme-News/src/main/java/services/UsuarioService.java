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

import domain.Comentario;
import domain.Estatus;
import domain.Informacion;
import domain.Reporte;
import domain.Sorteo;
import domain.Tasa;
import domain.Usuario;
import forms.UsuarioForm;
import repositories.UsuarioRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class UsuarioService {
	
	
	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private InformacionService informacionService;
	
	@Autowired
	private SorteoService sorteoService;
	
	@Autowired
	private TasaService tasaService;
	
	// Validator
	@Autowired
	@Qualifier("validator")
	private Validator validator;

	
	
	
	// Simple SCRUD methods----------------------------------------------------------------------------------------------
	public Usuario create(){
		// Authority
		Authority authority = new Authority();
		authority.setAuthority(Authority.USUARIO);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		
		// UserAccount
		UserAccount userAccount = new UserAccount();
		userAccount.setAuthorities(authorities);
		
		// Usuario
		Usuario usuario = new Usuario();
		usuario.setUserAccount(userAccount);
		usuario.setPuntos(0);
		usuario.setEstatus(Estatus.PRINCIPIANTE);
		usuario.setBanned(false);
		usuario.setReportes(new ArrayList<Reporte>());
		usuario.setSeguidores(new ArrayList<Usuario>());
		usuario.setSiguiendo(new ArrayList<Usuario>());
		usuario.setComentarios(new ArrayList<Comentario>());
		
		return usuario;
	}
	
	public Usuario save(Usuario usuario){
		Assert.notNull(usuario);
		return this.usuarioRepository.save(usuario);
	}
	
	public void delete(Usuario usuario){
		this.usuarioRepository.delete(usuario);
	}
	
	public Collection<Usuario> findAll(){
		return this.usuarioRepository.findAll();
	}
	
	public Usuario findOne(int id){
		Usuario user = this.usuarioRepository.findOne(id);
		return user;
	}
	
	
	// check password
	public boolean isPasswordCorrect(String pass1, String pass2){
		return pass1.equals(pass2);
	}
	
	// Reconstruct to UsuarioForm
	public Usuario reconstruc(UsuarioForm usuarioForm, BindingResult binding) {
		Usuario result = this.create();
		// UserAccount
		Authority authority = new Authority();
		authority.setAuthority(Authority.USUARIO);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
		
		result.setUserAccount(usuarioForm.getUserAccount());
		result.getUserAccount().setAuthorities(authorities);
		
		// Usuario
		result.setNombre(usuarioForm.getNombre());
		result.setApellidos(usuarioForm.getApellidos());
		result.setEmail(usuarioForm.getEmail());
		
		validator.validate(result, binding);

		return result;
	}
	
	// To edit a usuario's profile
	public Usuario reconstruct(Usuario usuario, BindingResult binding) {	
		Usuario result = usuario;
		
		// The pruned object is completed with the required fields
		registerOthersFields(result);

		validator.validate(result, binding);
		return result;
	}
	
	private void registerOthersFields(Usuario newOne) {
		Usuario oldOne = findByPrincipalToEdit();
		
		// Authority
		Authority authority = new Authority();
		authority.setAuthority(Authority.USUARIO);
		Collection<Authority> authorities = new ArrayList<Authority>();
		authorities.add(authority);
	
		newOne.setId(oldOne.getId());
		newOne.setUserAccount(oldOne.getUserAccount());		
		newOne.setBanned(oldOne.getBanned());
		newOne.setPuntos(oldOne.getPuntos());
		newOne.setEstatus(oldOne.getEstatus());
		newOne.setUsernameCopyForBan(oldOne.getUsernameCopyForBan());
		newOne.setSeguidores(oldOne.getSeguidores());
		newOne.setSiguiendo(oldOne.getSiguiendo());
		newOne.setComentarios(oldOne.getComentarios());
		newOne.setReportes(oldOne.getReportes());
		newOne.setInformacionCompartida(oldOne.getInformacionCompartida());
		newOne.setSorteos(oldOne.getSorteos());
	}
	
	private Usuario findByPrincipalToEdit() {
		Usuario result = (Usuario) actorService.findByPrincipal();
		return result;
	}




	//a�adido
	
	
	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}
	
	
	public Collection<Usuario> usuariosSiguiendo(){
		this.usuarioLogued();
		return findByPrincipal().getSiguiendo();
	}

	public Usuario findByPrincipal(){
		this.usuarioLogued();
		Usuario result;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		result = (Usuario) usuarioRepository.findByUserAccountId(userAccount.getId());
		return result;
	}
	
	public Collection<Informacion> findInformacionDeQuienSigues(){
		this.usuarioLogued();
		int id= findByPrincipal().getId();
		return usuarioRepository.findInformacionDeQuienSigues(id);
	}
	
	
	public Boolean seguirUsuario(Usuario usuario){
		this.usuarioLogued();
		Boolean res =false;
		assert usuario != null;
		Usuario usuarioLogued=  this.findByPrincipal();
		
		if(!usuarioLogued.getSiguiendo().contains(usuario)&&!usuario.equals(usuarioLogued)){
			usuarioLogued.getSiguiendo().add(usuario);
			usuario.getSeguidores().add(usuarioLogued);
			save(usuarioLogued);
			save(usuario);
			res=true;
		}
		return res;
	}
	
	public Boolean noSeguirUsuario(Usuario usuario){
		this.usuarioLogued();
		Boolean res =false;
		assert usuario != null;
		Usuario usuarioLogued=  this.findByPrincipal();
		
		if(usuarioLogued.getSiguiendo().contains(usuario)){
			usuarioLogued.getSiguiendo().remove(usuario);
			usuario.getSeguidores().remove(usuarioLogued);
			save(usuarioLogued);
			save(usuario);
			res=true;
		}
		
		return res;
	}
	
	
	public Boolean compartirInformacion(Informacion informacion){
		this.usuarioLogued();
		Boolean res =false;
		assert informacion != null;
		Usuario usuarioLogued=  this.findByPrincipal();
		if(!usuarioLogued.getInformacionCompartida().contains(informacion)){
			usuarioLogued.getInformacionCompartida().add(informacion);
			informacion.getUsuarios().add(usuarioLogued);
			this.save(usuarioLogued);
			informacionService.save(informacion);
			Double tasa=0.;
			if(usuarioLogued.getEstatus().equals(Estatus.PRINCIPIANTE)){
				tasa=tasaService.findpuntosPrincipiante();
			}else if(usuarioLogued.getEstatus().equals(Estatus.MAESTRO)){
				tasa=tasaService.findpuntosMaestro();
			}else tasa=tasaService.findpuntosModerador();
	
			usuarioLogued.setPuntos((int) (usuarioLogued.getPuntos()+tasa));
			
			res=true;
		}
		
		return res;
	}
	
	public void usuarioLogued(){
		Authority authority = new Authority();
		authority.setAuthority(Authority.USUARIO);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(authority),"Deberias ser un usuario para poder realizar esta operaci�n");
	}
	
	
	
	public Collection<Sorteo> findSorteosProximos(){
		this.usuarioLogued();
		return sorteoService.getSorteoRepository().findSorteosProximos();
	}
	
	public Integer apuntarseSorteo(Sorteo sorteo){
		this.usuarioLogued();
		Integer res =null;
		Usuario usuarioLogued=  this.findByPrincipal();
		assert sorteo != null;
		if(usuarioLogued.getSorteos().contains(sorteo)){
			
			res=0; //significa que usuario ya contenia el sortea y no deberia poder apuntarse
		}else if(usuarioLogued.getPuntos()<sorteo.getPuntosNecesarios()){
			res=1; //significa que el usuario no tiene suficientes puntos para apuntarse al sorteo
		}else {
			usuarioLogued.getSorteos().add(sorteo);
			sorteo.getUsuarios().add(usuarioLogued);
			usuarioLogued.setPuntos(usuarioLogued.getPuntos()-sorteo.getPuntosNecesarios());
			save(usuarioLogued);
			sorteoService.save(sorteo);
			res=2; // operacion realizada con exito
		}
		return res;
	}
	
	public Integer cambiarEstatus(){
		this.usuarioLogued();
		Integer res =null;
		Usuario usuarioLogued=  this.findByPrincipal();
		Tasa tasa=tasaService.getTasaRepository().findAll().get(0);
		if(usuarioLogued.getEstatus().equals(Estatus.PRINCIPIANTE)){
			if(usuarioLogued.getPuntos()>=tasa.getCosteVeterano()){
				usuarioLogued.setEstatus(Estatus.VETERANO);
				usuarioLogued.setPuntos(usuarioLogued.getPuntos()-tasa.getCosteVeterano());
				save(usuarioLogued);
				res=1; //significa que usuario pasa de principiante a veterano
			} else{
				res=2; //significa que usuario queria pasar de principiante a veterano pero no tenia puntos
			}
				
		}
		
		else if(usuarioLogued.getEstatus().equals(Estatus.VETERANO)){
			if(usuarioLogued.getPuntos()>=tasa.getCosteMaestro()){
				usuarioLogued.setEstatus(Estatus.MAESTRO);
				usuarioLogued.setPuntos(usuarioLogued.getPuntos()-tasa.getCosteMaestro());
				save(usuarioLogued);
				res=1; //significa que usuario pasa de veterano a maestro
			} else{
				res=2; //significa que usuario queria pasar de veterano a maestro pero no tenia puntos
			}
		}
		else if(usuarioLogued.getEstatus().equals(Estatus.MAESTRO)){
			res=3; //ya es maestro y no necesita aumentar m�s estatus
		}
		return res;
	}
	
	
	public List<Usuario> getUsuariosToBan() {
		return this.usuarioRepository.getUsersNotBanned();
	}

	public List<Usuario> getUsuariosToUnBan() {
		return this.usuarioRepository.getUsersBanned();
	}

	public Usuario saveBanUnban(int id) {
		Authority authority = new Authority();
		authority.setAuthority(Authority.MODERADOR);
		Assert.notNull(this.usuarioRepository.findOne(id));
		Assert.isTrue(LoginService.getPrincipal().getAuthorities()
				.contains(authority));
		Usuario toBanUnban = this.usuarioRepository.findOne(id);
		Boolean probe = toBanUnban.getBanned();
		if (toBanUnban.getBanned() == false) {

			toBanUnban.setUsernameCopyForBan(toBanUnban.getUserAccount()
					.getUsername());
			toBanUnban.getUserAccount().setUsername(null);
			toBanUnban.setBanned(true);
		} else {
			toBanUnban.getUserAccount().setUsername(
					toBanUnban.getUsernameCopyForBan());
			toBanUnban.setBanned(false);
		}
		Usuario saved = this.usuarioRepository.save(toBanUnban);
		Assert.isTrue(!probe.equals(toBanUnban.getBanned()));
		return saved;
	}
	












}
