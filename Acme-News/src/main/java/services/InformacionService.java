package services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import repositories.InformacionRepository;
import domain.Informacion;

@Service
@Transactional
public class InformacionService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private InformacionRepository informacionRepository;

	// Supporting services ----------------------------------------------------

	// Contructors ------------------------------------------------------------
	public InformacionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Informacion findOne(int informacionID) {
		Informacion result;

		result = informacionRepository.findOne(informacionID);

		return result;
	}

	public Informacion save(Informacion informacion) {
		assert informacion != null;

		Informacion result;

		result = informacionRepository.save(informacion);
		return result;
	}


	
	
	public InformacionRepository getInformacionRepository() {
		return informacionRepository;
	}
}
