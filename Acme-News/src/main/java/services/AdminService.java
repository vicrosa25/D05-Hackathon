
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrador;
import repositories.AdminRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdminService {

	// Manage Repository
	@Autowired
	private AdminRepository			adminRepository;

	// Supporting services
	@Autowired
	private ActorService			actorService;



	/*************************************
	 * CRUD methods
	 *************************************/
	public Administrador create() {
		// Initialice
		UserAccount userAccount = new UserAccount();
		Collection<Authority> authorities = new ArrayList<Authority>();
		Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		Administrador admin = new Administrador();
		admin.setUserAccount(userAccount);

		return admin;
	}

	public Collection<Administrador> findAll() {
		final Collection<Administrador> result = this.adminRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrador findOne(int adminID) {
		final Administrador result = this.adminRepository.findOne(adminID);
		Assert.notNull(result);
		return result;
	}

	public Administrador save(Administrador admin) {
		Assert.notNull(admin);
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		return this.adminRepository.save(admin);
	}
	
	public Administrador update(Administrador admin) {
		Assert.notNull(admin);
		Administrador result;
		Actor principal;
		UserAccount userAccount;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		userAccount = LoginService.getPrincipal();
		Assert.isTrue(userAccount.equals(admin.getUserAccount()));

		result = this.adminRepository.save(admin);

		return result;
	}

	public void delete(final Administrador admin) {
		Assert.notNull(admin);
		Assert.isTrue(admin.getId() != 0);
		this.adminRepository.delete(admin);
	}

	/*********************************************
	 * 
	 * Admin Dashboard Queries
	 * 
	 *********************************************/
	public Object[] query1() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query1();
	}

	public List<String> query2() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query2();
	}

	public Integer query3_1() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query3_1();
	}
	
	public Integer query3_2() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query3_2();
	}
	
	public Integer query3_3() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query3_3();
	}

	public Object[] query4() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query4();
	}

	public Integer query5() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query5();
	}

	public List<String> query6() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query6();
	}

	public List<String> query7() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query7();
	}

	public Integer query8() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query8();
	}

	public Integer query9() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query9();
	}
	
	
	public Integer query10() {
		Actor principal;

		// Check principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		return this.adminRepository.query10();
	}
	
	// Other methods
	public Administrador findByPrincipal() {
		Administrador result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrador findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);

		Administrador result;

		result = this.adminRepository.findByUserAccountId(userAccount.getId());

		return result;
	}
}
