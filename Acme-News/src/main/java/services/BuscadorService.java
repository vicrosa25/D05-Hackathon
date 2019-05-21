
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BuscadorRepository;
import domain.Buscador;
import domain.Noticia;
import domain.Usuario;

@Service
@Transactional
public class BuscadorService {

	// Manage Repository
	@Autowired
	private BuscadorRepository		buscadorRepository;

	// Other services
	@Autowired
	private UsuarioService			usuarioService;

	@Autowired
	private NoticiaService			noticiaService;

	//	@Autowired
	//	private ConfigurationsService	configurationsService; TODO


	// CRUD methods
	public Buscador create() {
		final Buscador result = new Buscador();

		final Calendar ultimaActualizacion = Calendar.getInstance();
		ultimaActualizacion.add(Calendar.YEAR, -1);

		result.setUltimaActualizacion(ultimaActualizacion.getTime());
		return this.buscadorRepository.save(result);
	}

	public Buscador findOne(final int buscadorID) {
		final Buscador result = this.buscadorRepository.findOne(buscadorID);
		Assert.notNull(result);

		return result;
	}

	public Buscador save(final Buscador buscador) {
		Assert.notNull(buscador);
		final Usuario principal = this.usuarioService.findByPrincipal();
		Assert.isTrue(principal.getBuscador().getId() == buscador.getId());

		final Buscador result = this.buscadorRepository.save(buscador);

		return result;
	}

	public void delete(final Buscador buscador) {
		Assert.notNull(buscador);
		final Usuario principal = this.usuarioService.findByPrincipal();
		Assert.isTrue(principal.getBuscador().getId() == buscador.getId());

		this.buscadorRepository.delete(buscador);
	}

	// Check if something has changed, if so the results are updated
	// If not, the results are updated if it has not been updated
	// in the specified time
	public Buscador checkChanges(final Buscador buscador) {
		final Buscador old = this.findOne(buscador.getId());
		if ( (buscador.getPalabra() != old.getPalabra()) || (buscador.getCategoria() != old.getCategoria())
			|| (buscador.getFechaFin() != old.getFechaFin()) || (buscador.getFechaInicio() != old.getFechaInicio()) ) {

			final Buscador saved = this.updateResults(buscador);
			return saved;

		} else {
			buscador.setNoticias(this.getResults(buscador));
			return buscador;
		}
	}

	// Check if it has passed enough time to update and return the results
	public Collection<Noticia> getResults(final Buscador buscador) {
		final Calendar siguienteActualizacion = Calendar.getInstance();
		siguienteActualizacion.setTime(buscador.getUltimaActualizacion());
		final Calendar actual = Calendar.getInstance();

		//siguienteActualizacion.add(Calendar.HOUR, this.configurationsService.getConfiguration().getCacheTime()); TODO

		if (actual.after(siguienteActualizacion))
			this.updateResults(buscador);
		return buscador.getNoticias();
	}

	public Buscador updateResults(final Buscador buscador) {
		Assert.notNull(buscador);
		final HashSet<Noticia> result = new HashSet<Noticia>(this.noticiaService.findAllPublicadas());

		if (buscador.getPalabra() != null)
			result.retainAll(this.buscadorRepository.filterByKeyword("%" + buscador.getPalabra() + "%"));

		if (buscador.getCategoria() != null && result.size()>0)
			result.retainAll(this.buscadorRepository.filterByCategoria(buscador.getCategoria()));

		if (buscador.getFechaFin() != null && result.size()>0)
			result.retainAll(this.buscadorRepository.filterByFechaFin(buscador.getFechaFin()));

		if (buscador.getFechaInicio() != null && result.size()>0)
			result.retainAll(this.buscadorRepository.filterByFechaInicio(buscador.getFechaInicio()));

		final ArrayList<Noticia> noticias = new ArrayList<Noticia>(result);

		//		if (result.size() > this.configurationsService.getConfiguration().getBuscadorMaxResult())
		//			buscador.setNoticias(noticias.subList(0, this.configurationsService.getConfiguration().getBuscadorMaxResult() - 1));
		//		else TODO
		buscador.setNoticias(noticias);
		buscador.setUltimaActualizacion(new Date());
		return this.buscadorRepository.save(buscador);
	}

	public Buscador clear(final Buscador buscador) {
		Assert.notNull(buscador);
		Assert.isTrue(this.usuarioService.findByPrincipal().getBuscador() == buscador);

		buscador.setPalabra(null);
		buscador.setFechaFin(null);
		buscador.setFechaInicio(null);
		buscador.setCategoria(null);
		buscador.setUltimaActualizacion(new Date());
		buscador.setNoticias(this.noticiaService.findAllPublicadas());

		return this.save(buscador);

	}

	public Collection<Noticia> filterByPalabra(String palabra) {
		Collection<Noticia> result = this.buscadorRepository.filterByKeyword("%"+palabra+"%");
		Assert.notNull(result);
		return result;
	}
}
