package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import datatypes.Cartera;

@Entity
@Access(AccessType.PROPERTY)
public class Periodista extends Actor {


	// Attributes -------------------------------------------------------------
	private String 					foto;
	private Cartera 				cartera;
	private Boolean					isBanned;
	private String 					usernameCopyForBan;

	// Relationships ----------------------------------------------------------
	private Agencia 				agencia;
	private Collection<Noticia> 	noticias;


	// Constructor
	public Periodista(){
		super();
	}


	// Getters and Setters	----------------------------------------------------------

	@URL
	public String getFoto() {
		return this.foto;
	}
	
	
	public void setFoto(String foto) {
		this.foto = foto;
	}

	
	@NotNull
	@Valid
	public Cartera getCartera() {
		return this.cartera;
	}
	
	
	public void setCartera(Cartera cartera) {
		this.cartera = cartera;
	}
	
	
	public String getUsernameCopyForBan() {
		return usernameCopyForBan;
	}


	
	public void setUsernameCopyForBan(String usernameCopyForBan) {
		this.usernameCopyForBan = usernameCopyForBan;
	}


	public Boolean getIsBanned() {
		return isBanned;
	}


	
	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}
	

	// Relationships  Getters and Setters	----------------------------------------------------------
	@ManyToOne(optional = true)
	public Agencia getAgencia() {
		return this.agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	@NotNull
	@OneToMany(mappedBy="periodista")
	public Collection<Noticia> getNoticias() {
		return this.noticias;
	}
	public void setNoticias(Collection<Noticia> noticias) {
		this.noticias = noticias;
	}
}