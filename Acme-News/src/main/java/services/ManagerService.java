
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrador;
import domain.Manager;
import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ManagerService {

	// Manage Repository
	@Autowired
	private ManagerRepository		managerRepository;

	// Supporting services
	
	@Autowired
	private ActorService			actorService;
	


	// CRUD methods
	public Manager create() {
		// Initialice
		UserAccount userAccount = new UserAccount();
		Collection<Authority> authorities = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setAuthority(Authority.MANAGER);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		
		// Settings
		Manager manager = new Manager();
		manager.setUserAccount(userAccount);

		return manager;

	}
	
	
	public Manager findOne(final int managerId) {
		final Manager result = this.managerRepository.findOne(managerId);
		Assert.notNull(result);

		return result;
	}
	
	public Collection<Manager> findAll() {
		Collection<Manager> result = this.managerRepository.findAll();
		Assert.notNull(result);

		return result;
	}
	
	

	public Manager save(Manager manager) {
		Assert.notNull(manager);
		Manager result;
		
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		result = this.managerRepository.save(manager);

		return result;
	}
	
	/************************************************************************************************/
	// Other business methods
	public Manager findByPrincipal() {
		Manager result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Manager findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Manager result;

		result = this.managerRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
