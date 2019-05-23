package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ComentarioRepository;
import domain.Comentario;
import domain.Estatus;
import domain.Informacion;
import domain.Usuario;

@Service
@Transactional
public class ComentarioService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TasaService tasaService;

	// Supporting services ----------------------------------------------------

	// Contructors ------------------------------------------------------------
	public ComentarioService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comentario create(Informacion informacionId) {
		Comentario result = new Comentario();
		result.setUsuario(this.usuarioService.findByPrincipal());

		result.setInformacion(informacionId);
		Date date = new Date();
		result.setFecha(date);


		return result;
	}

	public Comentario findOne(int comentarioID) {
		Comentario result;

		result = this.comentarioRepository.findOne(comentarioID);

		return result;
	}

	public Comentario save(Comentario comentario) {
		assert comentario != null;
		assert comentario.getTitulo() != null;
		assert comentario.getDescripcion() != null;
		Comentario result;

		result = this.comentarioRepository.save(comentario);

		Usuario usuarioLogued= this.usuarioService.findByPrincipal();
		Double tasa=0.;
		if(usuarioLogued.getEstatus().equals(Estatus.PRINCIPIANTE)){
			tasa=this.tasaService.findpuntosPrincipiante();
		}else if(usuarioLogued.getEstatus().equals(Estatus.MAESTRO)){
			tasa=this.tasaService.findpuntosMaestro();
		}else tasa=this.tasaService.findpuntosModerador();

		usuarioLogued.setPuntos((int) (usuarioLogued.getPuntos()+tasa));

		return result;
	}

	public Collection<Comentario> findAll() {
		Collection<Comentario> result = this.comentarioRepository.findAll();
		Assert.notNull(result);
		return result;
	}
}