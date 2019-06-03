
package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Agencia;
import domain.Evento;
import domain.Manager;
import domain.Periodista;
import repositories.AgenciaRepository;

@Service
@Transactional
public class AgenciaService {

	// Manage Repository
	@Autowired
	private AgenciaRepository	agenciaRepository;

	// Supporting services 
	@Autowired
	private ActorService		actorService;

	@Autowired
	private PeriodistaService	periodistaService;

	//	@Autowired
	//	private Validator validator;


	// CRUD methods -------------------------------------------------------------------------------------------
	public Agencia create() {
		Agencia result;
		Actor principal;
		Collection<Evento> eventos;
		Collection<Periodista> periodistas;

		// Check principal must be a Manager
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Manager.class, principal);

		eventos = new ArrayList<Evento>();
		periodistas = new ArrayList<Periodista>();

		// Create and settin 
		result = new Agencia();
		result.setImportancia((long) 1);
		result.setEventos(eventos);
		result.setPeriodistas(periodistas);

		return result;
	}

	public Agencia findOne(int agenciaId) {
		return agenciaRepository.findOne(agenciaId);
	}

	public Collection<Agencia> findAll() {
		return agenciaRepository.findAll();
	}

	public Agencia save(Agencia agencia) {
		Assert.notNull(agencia);
		Actor principal;
		Manager manager;
		Agencia saved;
		Agencia old;

		// Check principal must be a Manager
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Manager.class, principal);

		manager = (Manager) principal;

		if (agencia.getId() == 0) {
			agencia.setManager(manager);
			saved = this.agenciaRepository.save(agencia);
			manager.getAgencias().add(saved);
		} else {
			Assert.isTrue(manager.getAgencias().contains(agencia));
			old = this.agenciaRepository.findOne(agencia.getId());
			int capacity = old.getCapacidadDisponible();
			Assert.isTrue(agencia.getCapacidadDisponible() >= capacity, "No capacidad menor");
			saved = this.agenciaRepository.save(agencia);
		}

		return saved;
	}

	public Collection<Agencia> findAllNotFull() {
		return agenciaRepository.findAllNotFull();
	}

	public void delete(int agenciaId) {
		Actor 		principal;
		Manager 	manager;
		Agencia 	toDelete;
		
		
		toDelete = this.findOne(agenciaId);
		
		// Check the manager is the owner of the agency
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Manager.class, principal);
		manager = (Manager) principal;
		Assert.isTrue(manager.getAgencias().contains(toDelete));


		for (Periodista p : toDelete.getPeriodistas()) {
			p.setAgencia(null);
			this.periodistaService.save(p);
		}


		manager.removeAgencia(toDelete);

		this.agenciaRepository.delete(toDelete);
	}

	public Agencia periodistaEject(int periodistaId, int agenciaId) {
		Agencia agencia;
		Periodista periodista;
		Actor principal;

		// Check the principal must to be a Manager
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Manager.class, principal);

		agencia = this.findOne(agenciaId);
		periodista = this.periodistaService.findOne(periodistaId);

		periodista.setAgencia(null);
		this.periodistaService.save(periodista);

		agencia.getPeriodistas().remove(periodista);

		return this.save(agencia);
	}

	// Others Methods ------------------------------------------------------------------------------------------
	public void join(int agenciaId) {
		Agencia toJoin = this.findOne(agenciaId);
		Periodista logged = this.periodistaService.findByPrincipal();

		toJoin.addPeriodista(logged);
		logged.setAgencia(toJoin);

		agenciaRepository.save(toJoin);
		this.periodistaService.save(logged);
	}

	public void left(int agenciaId) {
		Agencia toLeft = this.findOne(agenciaId);
		Periodista logged = this.periodistaService.findByPrincipal();

		toLeft.removePeriodistas(logged);
		logged.setAgencia(null);

		this.agenciaRepository.save(toLeft);
		this.periodistaService.save(logged);
	}
}
