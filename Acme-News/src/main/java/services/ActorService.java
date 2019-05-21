package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {
	
	// Managed repository -----------------------------------------------------
	
	@Autowired
	private ActorRepository actorRepository;
	
	// Constructors -----------------------------------------------------------

	public ActorService() {
		super();
	}
	
	// CRUD methods
	
	public void deleteActor(Actor actor){
		this.actorRepository.delete(actor);
	}
	
	// Other business methods -------------------------------------------------

	public Actor findByPrincipal() {
		Actor result = findByUserAccount(LoginService.getPrincipal());
		return result;
	}
	
	private Actor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor result = actorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}
	
	public Actor findByUsername(String username){
		Actor result = actorRepository.findOneByName(username);
		return result;
	}
	
}