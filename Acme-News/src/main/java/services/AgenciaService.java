package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ActorRepository;
import repositories.AgenciaRepository;
import repositories.ManagerRepository;
import repositories.PeriodistaRepository;
import security.LoginService;
import domain.Agencia;
import domain.Manager;
import domain.Periodista;

@Service
@Transactional
public class AgenciaService {

	@Autowired
	private AgenciaRepository agenciaRepository;
	@Autowired
	private ActorRepository actorRepository;
	@Autowired
	private PeriodistaRepository periodistaRepository;
	@Autowired
	private ManagerRepository managerRepository;

	public AgenciaService() {
		super();
	}

	public Collection<Agencia> findAllNotFull() {
		return agenciaRepository.findAllNotFull();
	}

	public Collection<Agencia> findAll() {
		return agenciaRepository.findAll();
	}

	public void join(int agenciaId) {
		Agencia toJoin = agenciaRepository.findOne(agenciaId);
		String loggedUsername = LoginService.getPrincipal().getUsername();
		Periodista logged = (Periodista) actorRepository
				.findOneByName(loggedUsername);

		toJoin.addPeriodista(logged);
		logged.setAgencia(toJoin);

		agenciaRepository.save(toJoin);
		periodistaRepository.save(logged);
	}

	public void left(int agenciaId) {
		Agencia toJoin = agenciaRepository.findOne(agenciaId);
		String loggedUsername = LoginService.getPrincipal().getUsername();
		Periodista logged = (Periodista) actorRepository
				.findOneByName(loggedUsername);

		toJoin.removePeriodistas(logged);
		logged.setAgencia(null);

		agenciaRepository.save(toJoin);
		periodistaRepository.save(logged);
	}

	public void delete(int agenciaId) {
		Agencia toDelete = agenciaRepository.findOne(agenciaId);

		for (Periodista p : toDelete.getPeriodistas()) {
			p.setAgencia(null);
			periodistaRepository.save(p);
		}

		Manager manager = toDelete.getManager();
		manager.removeAgencia(toDelete);
		managerRepository.save(manager);

		agenciaRepository.delete(agenciaId);
	}

	public Agencia findOne(int agenciaId) {
		return agenciaRepository.findOne(agenciaId);
	}

	public void save(Agencia agencia) {
		agenciaRepository.save(agencia);
	}

	// --------------Reconstruct -----------
	public Agencia find() {
		List<Agencia> result = agenciaRepository.findAll();

		return result.get(0);
	}

	@Autowired
	private Validator validator;

	public Agencia reconstruct(Agencia agencia, BindingResult binding) {
		Agencia result = agencia;

		result.setId(find().getId());
		result.setPeriodistas(find().getPeriodistas());
		result.setManager(find().getManager());

		validator.validate(agencia, binding);

		return result;
	}

}
