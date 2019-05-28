
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

	@Autowired
	private ManagerService		managerService;

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
		
		// Check principal must be a Manager
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Manager.class, principal);
		
		manager = (Manager) principal;
		
		if(agencia.getId() == 0) {
			agencia.setManager(manager);
			saved = this.agenciaRepository.save(agencia);
			manager.getAgencias().add(saved);
		} else {
			saved = this.agenciaRepository.save(agencia);
		}
		
		return saved;
	}

	public Collection<Agencia> findAllNotFull() {
		return agenciaRepository.findAllNotFull();
	}

	public void delete(int agenciaId) {
		Agencia toDelete = this.findOne(agenciaId);

		for (Periodista p : toDelete.getPeriodistas()) {
			p.setAgencia(null);
			this.periodistaService.save(p);
		}

		Manager manager = toDelete.getManager();
		manager.removeAgencia(toDelete);
		this.managerService.save(manager);

		agenciaRepository.delete(agenciaId);
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

	// --------------Reconstruct --------------------------------------------------------------------------------
	//	public Agencia find() {
	//		List<Agencia> result = agenciaRepository.findAll();
	//
	//		return result.get(0);
	//	}
	//
	//	
	//
	//	public Agencia reconstruct(Agencia agencia, BindingResult binding) {
	//		Agencia result = agencia;
	//
	//		result.setId(find().getId());
	//		result.setPeriodistas(find().getPeriodistas());
	//		result.setManager(find().getManager());
	//
	//		validator.validate(agencia, binding);
	//
	//		return result;
	//	}

}
