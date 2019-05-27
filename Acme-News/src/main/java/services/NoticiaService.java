
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import datatypes.Cartera;
import domain.Categoria;
import domain.Comentario;
import domain.Estado;
import domain.Informacion;
import domain.Moderador;
import domain.Noticia;
import domain.Periodista;
import domain.Reporte;
import domain.Usuario;
import repositories.NoticiaRepository;

@Service
@Transactional
public class NoticiaService {

	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private NoticiaRepository	noticiaRepository;

	// Supporting services ---------------------------------------------------------------------------------------------
	@Autowired
	private PeriodistaService	periodistaService;
	@Autowired
	private ModeradorService	moderadorService;
	@Autowired
	private TasaService			tasaService;
	@Autowired
	private ReporteService		reporteService;
	@Autowired
	private ComentarioService	comentarioService;


	public NoticiaService() {
		super();
	}

	// Simple SCRUD methods----------------------------------------------------------------------------------------------
	public Noticia create() {
		Noticia noticia = new Noticia();

		noticia.setEstado(Estado.PENDIENTE);
		noticia.setIsBanned(false);
		noticia.setFecha(new Date());
		noticia.setNumeroVisitas((long) 0);

		noticia.setComentarios(new ArrayList<Comentario>());
		noticia.setNoticiasRelacionadas(new ArrayList<Noticia>());
		noticia.setReportes(new ArrayList<Reporte>());
		noticia.setUsuarios(new ArrayList<Usuario>());

		return noticia;
	}

	public Noticia save(Noticia noticia) {
		Assert.notNull(noticia);

		return this.noticiaRepository.save(noticia);
	}
	public Noticia saveNew(Noticia noticia) {
		Assert.notNull(noticia);

		Periodista periodista = this.periodistaService.findByPrincipal();
		Assert.notNull(periodista);

		noticia.setPeriodista(periodista);

		return this.save(noticia);
	}

	public void delete(Noticia noticia) {
		Assert.notNull(noticia);
		Assert.isTrue(noticia.getPeriodista() == this.periodistaService.findByPrincipal());

		for (Noticia n : noticia.getNoticiasRelacionadas()) {
			Collection<Noticia> noticiasRelacionadas = n.getNoticiasRelacionadas();
			noticiasRelacionadas.remove(noticia);
			n.setNoticiasRelacionadas(noticiasRelacionadas);
		}
		for (Usuario usuario : noticia.getUsuarios()) {
			Collection<Informacion> informacionCompartida = usuario.getInformacionCompartida();
			informacionCompartida.remove(noticia);
			usuario.setInformacionCompartida(informacionCompartida);
		}

		Iterator<Comentario> comentarios = new ArrayList<Comentario>(noticia.getComentarios()).iterator();
		Iterator<Reporte> reportes = new ArrayList<Reporte>(noticia.getReportes()).iterator();

		while (comentarios.hasNext()) {
			Comentario next = comentarios.next();
			this.comentarioService.delete(next);
			comentarios.remove();
		}

		while (reportes.hasNext()) {
			Reporte next = reportes.next();
			this.reporteService.delete(next);
			reportes.remove();
		}

		this.noticiaRepository.delete(noticia);
	}

	public Collection<Noticia> findAll() {
		Collection<Noticia> result = new ArrayList<>();
		result = this.noticiaRepository.findAll();
		return result;
	}

	public Collection<Noticia> findAllPublicadas() {
		Collection<Noticia> result = new ArrayList<>();
		result = this.noticiaRepository.noticiasPublicadas();
		return result;
	}

	public Noticia findOne(int id) {
		Noticia result;
		result = this.noticiaRepository.findOne(id);
		return result;
	}

	// Listing methods -------------------------------------------------------------------------

	public Collection<Noticia> buscarPorCategoria(Categoria categoria) {
		return this.noticiaRepository.findByCategoria(categoria);
	}

	public Collection<Noticia> buscarPorPalabraClave(String keyword) {
		return this.noticiaRepository.searchByKeyword(keyword.trim());
	}

	public Collection<Noticia> buscarPorPeriodista(Integer periodistaId) {
		return this.noticiaRepository.searchByJournalist(periodistaId);
	}
	// DISPLAY----------------------------------------------------------------------------------
	public Noticia findOneToDisplay(int id) {
		Noticia result;
		Periodista autor;
		Cartera cartera;

		result = this.noticiaRepository.findOne(id);
		autor = result.getPeriodista();
		cartera = autor.getCartera();

		Assert.notNull(result);
		Assert.notNull(autor);

		result.setNumeroVisitas(result.getNumeroVisitas() + 1);

		cartera.setSaldoAcumulado(round(cartera.getSaldoAcumulado() + this.tasaService.findOne().getTasaVisita(), 2));
		cartera.setSaldoAcumuladoTotal(round(cartera.getSaldoAcumuladoTotal() + this.tasaService.findOne().getTasaVisita(), 2));
		autor.setCartera(cartera);
		this.periodistaService.save(autor);

		return this.noticiaRepository.save(result);
	}

	// Ban ----------------------------------------------------------------------------
	public Collection<Noticia> getNoticiasParaBanear() {
		return this.noticiaRepository.noticiasParaBanear();
	}
	public void banearNoticia(Noticia noticia) {
		this.moderadorService.findByPrincipal();
		noticia.setEstado(Estado.DENEGADA);
		noticia.setIsBanned(true);
		this.noticiaRepository.save(noticia);
	}

	// ACEPTAR Y DENEGAR NOTICIAS

	public Collection<Noticia> getNoticiasPendientes() {
		return this.noticiaRepository.noticiasPendientes();
	}
	public void cobrarReolucionNoticia(Moderador moderador) {
		Cartera cartera = moderador.getCartera();

		cartera.setSaldoAcumulado(round(cartera.getSaldoAcumulado() + this.tasaService.findOne().getTasaModerador(), 2));
		cartera.setSaldoAcumuladoTotal(round(cartera.getSaldoAcumuladoTotal() + this.tasaService.findOne().getTasaModerador(), 2));
		moderador.setCartera(cartera);
		this.moderadorService.save(moderador);
	}

	public void aceptarNoticia(Noticia noticia) {
		Moderador moderador;

		moderador = this.moderadorService.findByPrincipal();
		this.cobrarReolucionNoticia(moderador);

		noticia.setEstado(Estado.PUBLICADA);
		this.noticiaRepository.save(noticia);
	}
	public void denegarNoticia(Noticia noticia) {
		Moderador moderador;

		moderador = this.moderadorService.findByPrincipal();
		this.cobrarReolucionNoticia(moderador);

		noticia.setEstado(Estado.DENEGADA);
		this.noticiaRepository.save(noticia);
	}
	// other methods

	protected static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public Noticia findOneByName(String noticiaTitulo) {
		Noticia result = this.noticiaRepository.findOneByName(noticiaTitulo);
		Assert.notNull(result);
		return result;
	}
}
