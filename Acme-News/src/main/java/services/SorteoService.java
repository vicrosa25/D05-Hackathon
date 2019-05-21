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

import repositories.SorteoRepository;
import repositories.UsuarioRepository;
import domain.Sorteo;
import domain.Usuario;

@Service
@Transactional
public class SorteoService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private SorteoRepository sorteoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	// Supporting services ----------------------------------------------------

	// Contructors ------------------------------------------------------------
	public SorteoService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Sorteo findOne(int sorteoID) {
		Sorteo result;

		result = sorteoRepository.findOne(sorteoID);

		return result;
	}

	public Sorteo save(Sorteo sorteo) {
		assert sorteo != null;

		Sorteo result;

		result = sorteoRepository.save(sorteo);
		return result;
	}


	
	
	public SorteoRepository getSorteoRepository() {
		return sorteoRepository;
	}
	
	public void delete(Sorteo sorteo){
		Collection<Usuario> usuarios = this.usuarioRepository.findAll() ;

			if(sorteo.getFechaVencimiento().after(new Date()) || sorteo.getGanador().equals("")){
				throw new IllegalArgumentException("Sorteo No Finalizado o sin ganador, no puede borrarlo");
			}
			else{
				
				for(Usuario s: usuarios){
					if(s.getSorteos().contains(sorteo)){
						s.getSorteos().remove(s);
						this.usuarioRepository.save(s);
					}
				}
				this.sorteoRepository.delete(sorteo);
			}
		
	}


	public Sorteo create(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Sorteo sorteo= new Sorteo();
		sorteo.setUsuarios(usuarios);
		sorteo.setGanador("");
		return sorteo;
	}
	
	public void computeWinners(){
		List<Usuario> users= new ArrayList<Usuario>();
		Collection<Usuario> usuarios = this.usuarioRepository.findAll();
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
