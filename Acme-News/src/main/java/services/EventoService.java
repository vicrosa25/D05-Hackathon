package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EventoRepository;
import domain.Actor;
import domain.Comentario;
import domain.Evento;
import domain.Manager;
import domain.Usuario;


@Service
@Transactional
public class EventoService {

	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private EventoRepository 	eventoRepository;


	// Supporting services ---------------------------------------------------------------------------------------------
	@Autowired
	private ComentarioService 	comentarioService;

	@Autowired
	private ManagerService 		managerService;

	@Autowired
	private ActorService		actorService;


	public EventoService() {
		super();
	}

	// Simple SCRUD methods----------------------------------------------------------------------------------------------
	public Evento create(){
		Evento evento = new Evento();

		evento.setUsuarios(new ArrayList<Usuario>());
		evento.setComentarios(new ArrayList<Comentario>());

		return evento;
	}

	public Evento save(Evento evento){
		Actor principal;
		Assert.notNull(evento);

		// Check principal must be a Manager
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Manager.class, principal);


		return this.eventoRepository.save(evento);
	}

	public Evento saveNew(Evento evento){
		Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.notNull(evento);
		Assert.isTrue(manager.getAgencias().contains(evento.getAgencia()));

		evento = this.eventoRepository.save(evento);
		evento.getAgencia().getEventos().add(evento);
		return evento;
	}

	public void delete(Evento evento){
		Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.notNull(evento);
		Assert.isTrue(manager.getAgencias().contains(evento.getAgencia()));

		evento.getAgencia().getEventos().remove(evento);
		Iterator<Comentario> comentarios	= new ArrayList<Comentario>(evento.getComentarios()).iterator();

		while (comentarios.hasNext()) {
			Comentario next = comentarios.next();
			this.comentarioService.delete(next);
			comentarios.remove();
		}

		for(Usuario usuario:evento.getUsuarios()){
			usuario.getInformacionCompartida().remove(evento);
		}

		this.eventoRepository.delete(evento);
	}

	public Collection<Evento> findAll(){
		Collection<Evento> result = new ArrayList<>();
		result = this.eventoRepository.findAll();
		return result;
	}

	public Collection<Evento> findActualEvents(){
		return this.eventoRepository.listActualEvents();
	}

	public Evento findOne(int id){
		Evento result;
		result = this.eventoRepository.findOne(id);
		return result;
	}

	public Collection<Evento> buscarPorManager(Manager manager){
		Assert.notNull(manager);
		Collection<Evento> result = this.eventoRepository.searchByManager(manager.getId());
		return result;
	}

	// other methods

	protected static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}