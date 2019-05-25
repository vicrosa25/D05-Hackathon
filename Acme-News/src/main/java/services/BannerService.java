package services;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrador;
import domain.Banner;
import repositories.BannerRepository;
import security.Authority;
import security.LoginService;

@Service
@Transactional
public class BannerService {
	
	// Managed repository------------------------------------
	@Autowired
	private BannerRepository bannerRepository;
	
	
	@Autowired
	private ActorService 	 actorService;
	
	
	// Simple SCRUD methods------------------------------------
	public Banner create() {
		Banner result;
		
		result = new Banner();
		
		return result;
	}
	
	public Collection<Banner> findAll() {
		return this.bannerRepository.findAll();
	}
	
	public Banner findOne(int bannerId) {
		Banner result;
		
		result = this.bannerRepository.findOne(bannerId);
		Assert.notNull(result);
		
		return result;
	}
	
	
	public Banner save(Banner banner){
		Assert.notNull(banner);
		Banner saved;
		
		// Check principal is an Admin
		Object principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);
		
		saved = this.bannerRepository.save(banner);
		
		return saved;
	}
	
	
	public void delete(Banner banner) {
		Assert.notNull(banner);

		// Check principal is an Admin
		Object principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrador.class, principal);

		Assert.isTrue(banner.getId() != 0);

		this.bannerRepository.delete(banner);
	}
	
	
	public Banner getBanner(){
		Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(authority));
		Banner banner= bannerRepository.findAll().get(0);
		Assert.notNull(banner);
		return  banner;
	}
	
	public Banner getBannerWellcome(){
		Banner banner= bannerRepository.findAll().get(0);
		Assert.notNull(banner);
		return  banner;
	}
	

	
	@SuppressWarnings("unused")
	public void isUrl(Collection<String> urls) throws MalformedURLException {
		for(String url:urls){
			URL us = new URL(url);
		}
	   
	
	}
	

}
