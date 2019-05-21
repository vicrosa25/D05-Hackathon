package services;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
	
	
	// Constructor
	public BannerService(){
		super();
	}
	
	// Simple SCRUD methods------------------------------------

	
	public Banner save(Banner o){
		Assert.notNull(o);
		return this.bannerRepository.save(o);
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
