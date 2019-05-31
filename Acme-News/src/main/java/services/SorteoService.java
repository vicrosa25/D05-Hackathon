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

import domain.Sorteo;
import domain.Usuario;
import repositories.SorteoRepository;

@Service
@Transactional
public class SorteoService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SorteoRepository sorteoRepository;
	
	// Supporting services
	@Autowired
	private UsuarioService	 usuarioService;

	// Supporting services ----------------------------------------------------

	// Contructors ------------------------------------------------------------
	public SorteoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Sorteo create(){
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
		assert sorteo != null;

		Sorteo result;

		result = sorteoRepository.save(sorteo);
		return result;
	}

	
	public void delete(Sorteo sorteo){
		Collection<Usuario> usuarios = this.usuarioService.findAll();

		if(sorteo.getFechaVencimiento().after(new Date()) || sorteo.getGanador().equals("")){
			throw new IllegalArgumentException("Sorteo No Finalizado o sin ganador, no puede borrarlo");
		}
		else{

			for(Usuario usuario : usuarios){
				if(usuario.getSorteos().contains(sorteo)) {
					usuario.getSorteos().remove(sorteo);
					sorteo.getUsuarios().remove(usuario);
				}
			}
			this.sorteoRepository.delete(sorteo);
		}
		
	}

	
	public void computeWinners(){
		List<Usuario> users= new ArrayList<Usuario>();
		Collection<Usuario> usuarios = this.usuarioService.findAll();
		Collection<Sorteo> sorteos= this.sorteoRepository.findAll();
		if(!sorteos.isEmpty() && !usuarios.isEmpty()){
		users.addAll(usuarios);
		int tamaño= users.size()-1;
		Random r= new Random();
		int gana=r.nextInt(tamaño);
		
		for(Sorteo s: sorteos){
			if(s.getFechaVencimiento().before(new Date()) && s.getGanador().equals("")){
				s.setGanador(users.get(gana).getNombre() +" , email: "+users.get(gana).getEmail());
			}
			
		}
		}
	}


	public Sorteo save2(Sorteo sorteo){
		Assert.notNull(sorteo);
		
		if(sorteo.getFechaInicio().after(sorteo.getFechaVencimiento()) ||
				sorteo.getFechaInicio().before(new Date())){
			throw new IllegalArgumentException("No puede editar un concurso en progreso o que ya ha terminado.");
		}
		else{
			return this.sorteoRepository.save(sorteo);	
		}
		
	}
}
