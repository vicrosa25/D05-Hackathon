package services;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import repositories.ReporteRepository;
import domain.Noticia;
import domain.Reporte;



@Service
@Transactional
public class ReporteService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ReporteRepository reporteRepository;
	
	@Autowired
	private UsuarioService usuarioService;

	// Supporting services ----------------------------------------------------

	// Contructors ------------------------------------------------------------
	public ReporteService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public Reporte create(Noticia noticiaId) {
		Reporte result = new Reporte();
		assert noticiaId != null;
		result.setUsuario(usuarioService.findByPrincipal());
		result.setNoticia(noticiaId);

		return result;
	}
	
	


	

	public Reporte findOne(int reporteID) {
		Reporte result;

		result = reporteRepository.findOne(reporteID);

		return result;
	}

	public Reporte save(Reporte reporte) {
		assert reporte != null;

		Reporte result;

		result = reporteRepository.save(reporte);
		return result;
	}


	
	
	public ReporteRepository getReporteRepository() {
		return reporteRepository;
	}
	

}
