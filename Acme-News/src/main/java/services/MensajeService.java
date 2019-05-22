package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MensajeRepository;
import domain.Mensaje;

@Service
@Transactional
public class MensajeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MensajeRepository mensajeRepository;

	// Supporting services
	@Autowired
	private UsuarioService usuarioService;

	// Constructors -----------------------------------------------------------

	public MensajeService() {
		super();
	}

	// CRUD methods

	public Mensaje create(){
		Mensaje result = new Mensaje();
		result.setMomento(new Date());

		return result;
	}

	public Mensaje save(Mensaje mensaje){
		Assert.isTrue(mensaje.getEmisor()
			.equals(this.usuarioService.findByPrincipal()));

		Mensaje result = this.mensajeRepository.save(mensaje);

		return result;
	}

	// Other business methods -------------------------------------------------

}