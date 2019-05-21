package services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TasaRepository;
import domain.Tasa;

@Service
@Transactional
public class TasaService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private TasaRepository tasaRepository;

	// Supporting services ----------------------------------------------------

	// Contructors ------------------------------------------------------------
	public TasaService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Tasa findOne(int tasaID) {
		Tasa result;

		result = tasaRepository.findOne(tasaID);

		return result;
	}
	
	public Tasa findOne(){
		Tasa result;
		result = this.tasaRepository.findAll().get(0);
		Assert.notNull(result);
		return result;
	}

	public Tasa save(Tasa tasa) {
		assert tasa != null;

		Tasa result;

		result = tasaRepository.save(tasa);
		return result;
	}
	
	public TasaRepository getTasaRepository() {
		return tasaRepository;
	}
	
	// Miscellaneous ----------------------------------------------------------

	public void flush() {
		tasaRepository.flush();
	}

	// Other business methods -------------------------------------------------
	
	public Double findcosteMaestro() {
		return tasaRepository.findcosteMaestro();
	}
	
	public Double findcosteVeterano() {
		return tasaRepository.findcosteVeterano();
	}
	
	public Double findpuntosMaestro() {
		return tasaRepository.findpuntosMaestro();
	}
	
	public Double findpuntosModerador() {
		return tasaRepository.findpuntosModerador();
	}
	
	public Double findpuntosPrincipiante() {
		return tasaRepository.findpuntosPrincipiante();
	}
	

	
	public Double findTasaModerador() {
		return tasaRepository.findTasaModerador();
	}
	
	public Double findTasaVisita() {
		return tasaRepository.findTasaVisita();
	}
	
	
	public Tasa find() {
		List<Tasa> result = tasaRepository.findAll();
		
		return result.get(0);
	}
	
	//--------------Reconstruct -----------
	
	@Autowired
	private Validator validator;
	
	public Tasa reconstruct(Tasa tasa, BindingResult binding) {
		Tasa result = tasa;
		
		result.setId(find().getId());
		
		validator.validate(tasa, binding);
		
		return result;
	}
}