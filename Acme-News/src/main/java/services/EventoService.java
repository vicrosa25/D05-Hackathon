package services;


import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ComentarioRepository;
import repositories.EventoRepository;
import datatypes.Cartera;
import domain.Comentario;
import domain.Evento;
import domain.Informacion;
import domain.Periodista;
import domain.Usuario;


@Service
@Transactional
public class EventoService {
	
	// Managed repository------------------------------------------------------------------------------------------------
	@Autowired
	private EventoRepository eventoRepository;
	
	
	// Supporting services ---------------------------------------------------------------------------------------------
	@Autowired
	private PeriodistaService periodistaService;
	@Autowired
	private ComentarioRepository comentarioRepository;


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
		Assert.notNull(evento);
		return this.eventoRepository.save(evento);
	}
	
	public Evento saveNew(Evento evento){
		Periodista periodista = this.periodistaService.findByPrincipal();
		Assert.notNull(periodista);
		evento.setPeriodista(periodista);
		
		Cartera cartera = periodista.getCartera();
				
		cartera.setSaldoAcumulado(
			round(cartera.getSaldoAcumulado()+periodista.getAgencia().getTasa(),2));
		cartera.setSaldoAcumuladoTotal(
			round(cartera.getSaldoAcumuladoTotal()+periodista.getAgencia().getTasa(),2));
		periodista.setCartera(cartera);
		
		this.periodistaService.save(periodista);
		
		return this.save(evento);
	}
	
	public void delete(Evento evento){
		Assert.notNull(evento);
		Assert.isTrue(evento.getPeriodista() == this.periodistaService.findByPrincipal());
		
		for(Usuario usuario:evento.getUsuarios()){
			Collection<Informacion> informacionCompartida = usuario.getInformacionCompartida();
			informacionCompartida.remove(evento);
			usuario.setInformacionCompartida(informacionCompartida);
		}
		for(Comentario c:evento.getComentarios()){
			this.comentarioRepository.delete(c.getId());
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
	
	public EventoRepository getEventoRepository() {
		return eventoRepository;
	}
	
	public Collection<Evento> buscarPorPeriodista(Integer periodistaId){
		return this.eventoRepository.searchByJournalist(periodistaId);
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