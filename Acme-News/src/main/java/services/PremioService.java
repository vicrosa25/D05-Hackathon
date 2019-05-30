package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrador;
import domain.Premio;
import domain.Sorteo;
import repositories.PremioRepository;
import repositories.SorteoRepository;


@Service
@Transactional
public class PremioService {

	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private PremioRepository premioRepository;
	

	// Supporting services
	@Autowired
	private SorteoRepository sorteoRepository;
	
	@Autowired
	private ActorService	 actorService;


	
	// CRUD methods
	public Premio create(){
		Actor principal;
		Premio premio;
		
		// Check principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		
		premio= new Premio();
		return premio;
	}
	
	public Premio findOne(int premioId) {
		return this.premioRepository.findOne(premioId);
	}
	
	
	public Collection<Premio> findAll() {
		return this.premioRepository.findAll();
	}

	public Premio save(Premio premio){
		Assert.notNull(premio);
		return this.premioRepository.save(premio);
	}
	
	public void delete(Premio premio){
		Collection<Sorteo> sorteos = this.sorteoRepository.findAll() ;
		int test = 0;
		
		for(Sorteo s:sorteos){
			if(s.getPremio().equals(premio)){
				test++;
			}
		}
		if (test > 0){
			throw new IllegalArgumentException("Este premio esta en sorteos");
		}
		else {
			this.premioRepository.delete(premio);
		}
	}

}