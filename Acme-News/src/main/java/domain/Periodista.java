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
	private String foto;
	private Cartera cartera;
	
	// Relationships ----------------------------------------------------------
	private Agencia agencia;
	private Collection<Informacion> informacion;
	

	// Constructor
	public Periodista(){
		super();
	}
	
	
	// Getters and Setters	----------------------------------------------------------
	
	@URL
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	@NotNull
	@Valid
	public Cartera getCartera() {
		return cartera;
	}
	public void setCartera(Cartera cartera) {
		this.cartera = cartera;
	}
	
	
	
	// Relationships    Getters and Setters	----------------------------------------------------------
	
	@ManyToOne(optional = true)
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
//	
	@NotNull
	@OneToMany(mappedBy="periodista")
	public Collection<Informacion> getInformacion() {
		return informacion;
	}
	public void setInformacion(Collection<Informacion> informacion) {
		this.informacion = informacion;
	}
	
	
	

}
