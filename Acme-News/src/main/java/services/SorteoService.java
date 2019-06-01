package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrador;
import domain.Sorteo;
import domain.Usuario;
import repositories.SorteoRepository;

@Service
@Transactional
public class SorteoService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SorteoRepository 	sorteoRepository;
	
	// Supporting services
	@Autowired
	private ActorService		actorService;



	// Contructors ------------------------------------------------------------
	public SorteoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Sorteo create(){
		Actor principal;
		
		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Sorteo sorteo= new Sorteo();
		sorteo.setUsuarios(usuarios);
		sorteo.setGanador("");
		return sorteo;
	}

	public Sorteo findOne(int sorteoID) {
		Sorteo result;

		result = sorteoRepository.findOne(sorteoID);

		return result;
	}
	
	
	public Collection<Sorteo> findAll() {
		return this.sorteoRepository.findAll();
	}

	
	
	public Sorteo save(Sorteo sorteo) {
		Assert.notNull(sorteo);
		Sorteo result;
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		//Assert.isInstanceOf(Administrador.class, principal);
		Assert.isTrue(principal.getClass().isInstance(new Administrador()) || principal.getClass().isInstance(new Usuario()));
		
		// Assert date
		Assert.isTrue(sorteo.getFechaInicio().before(sorteo.getFechaVencimiento()));
		

		result = sorteoRepository.save(sorteo);
		return result;
	}

	
	public void delete(Sorteo sorteo){
		Collection<Usuario> usuarios;
		Actor principal;
		
		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		usuarios = new ArrayList<>(sorteo.getUsuarios());
		

		if(sorteo.getFechaVencimiento().after(new Date()) || sorteo.getGanador().equals("")){
			throw new IllegalArgumentException("Sorteo No Finalizado o sin ganador, no puede borrarlo");
		}
		else{

			for(Usuario usuario : usuarios){
				usuario.getSorteos().remove(sorteo);
				sorteo.getUsuarios().remove(usuario);
			}
			this.sorteoRepository.delete(sorteo);
		}
		
	}

	
	public void computeWinner(int sorteoId){
		Actor principal;
		
		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		Sorteo sorteo = this.sorteoRepository.findOne(sorteoId);
		Assert.notNull(sorteo);
		
		List<Usuario> users= new ArrayList<Usuario>();
		
		Collection<Usuario> usuarios = sorteo.getUsuarios();
		
		if(!usuarios.isEmpty()){
			users.addAll(usuarios);
			int tamaño= users.size()-1;
			Random r= new Random();
			int gana = r.nextInt(tamaño);
			
			sorteo.setGanador(users.get(gana).getNombre() + " , email: " + users.get(gana).getEmail());
		}
		this.save(sorteo);
	}

	public Collection<Sorteo> findSorteosProximos() {
		return this.sorteoRepository.findSorteosProximos();
	}
	
	public Sorteo findOneByName(String titulo) {
		Sorteo result = this.sorteoRepository.findOneByName(titulo);
		Assert.notNull(result);
		
		return result;
	}
	
	// s.getFechaVencimiento().before(new Date()) &&
	
	
	
	
	
	
	
	
	
	
}
