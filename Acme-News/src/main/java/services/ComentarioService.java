package services;




import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
		result.setUsuario(usuarioService.findByPrincipal());
		
		result.setInformacion(informacionId);
		Date date = new Date();
		result.setFecha(date);
		

		return result;
	}
	
	


	

	public Comentario findOne(int comentarioID) {
		Comentario result;

		result = comentarioRepository.findOne(comentarioID);

		return result;
	}

	public Comentario save(Comentario comentario) {
		assert comentario != null;
		assert comentario.getTitulo() != null;
		assert comentario.getDescripcion() != null;
		Comentario result;

		result = comentarioRepository.save(comentario);
		
		Usuario usuarioLogued= usuarioService.findByPrincipal();
		Double tasa=0.;
		if(usuarioLogued.getEstatus().equals(Estatus.PRINCIPIANTE)){
			tasa=tasaService.findpuntosPrincipiante();
		}else if(usuarioLogued.getEstatus().equals(Estatus.MAESTRO)){
			tasa=tasaService.findpuntosMaestro();
		}else tasa=tasaService.findpuntosModerador();

		usuarioLogued.setPuntos((int) (usuarioLogued.getPuntos()+tasa));
		
		return result;
	}


	
	
	public ComentarioRepository getComentarioRepository() {
		return comentarioRepository;
	}
	

}
