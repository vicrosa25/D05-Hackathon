package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Reporte extends DomainEntity {
	
	
	// Attributes -------------------------------------------------------------
	private String texto;
	
	// Relationships ----------------------------------------------------------
	private Noticia noticia;
	private Usuario usuario;
	
	
	// Constructor   -----------------------------------------------------
	public Reporte(){
		super();
	}
	
	
	// Getters and Setters ----------------------------------------------------------
	
	@NotBlank
	public String getTexto() {
		return this.texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}


	// Relationships Getters and Setters ----------------------------------------------------------
	@NotNull
	@ManyToOne(optional = false)
	public Noticia getNoticia() {
		return noticia;
	}
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
	
	@NotNull
	@ManyToOne(optional = false)
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}