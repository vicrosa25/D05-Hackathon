package services;




import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		result.setUsuario(this.usuarioService.findByPrincipal());
		result.setNoticia(noticiaId);

		return result;
	}

	public Reporte findOne(int reporteID) {
		Reporte result = this.reporteRepository.findOne(reporteID);
		Assert.notNull(result);

		return result;
	}

	public Collection<Reporte> findAll() {
		Collection<Reporte> result = this.reporteRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Reporte save(Reporte reporte) {
		Assert.notNull(reporte);
		Reporte result = this.reporteRepository.save(reporte);

		return result;
	}

	public void delete(Reporte reporte) {
		Assert.notNull(reporte);
		reporte.getUsuario().getReportes().remove(reporte);
		reporte.getNoticia().getReportes().remove(reporte);

		this.reporteRepository.delete(reporte);
	}
}