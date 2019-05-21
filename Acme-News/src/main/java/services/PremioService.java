package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import domain.Premio;
import domain.Sorteo;
import repositories.PremioRepository;
import repositories.SorteoRepository;


@Service
@Transactional
public class PremioService {

	// Managed
	// repository------------------------------------------------------------------------------------------------
	@Autowired
	private PremioRepository premioRepository;
	@Autowired
	private SorteoRepository sorteoRepository;


	public PremioRepository getPremioRepository() {
		return premioRepository;
	}

	public Premio create(){
		Premio premio= new Premio();
		return premio;
	}

	public Premio save(Premio premio){
		Assert.notNull(premio);
		return this.premioRepository.save(premio);
	}
	
	public void delete(Premio premio){
		Collection<Sorteo> sorteos = this.sorteoRepository.findAll() ;
		int test=0;
			for(Sorteo s:sorteos){
				if(s.getPremio().equals(premio)){
					test++;
					}
		}
			if(test>0){
				throw new IllegalArgumentException("Este premio esta en sorteos");
			}
			else{
				this.premioRepository.delete(premio);
			}
		
	}

}