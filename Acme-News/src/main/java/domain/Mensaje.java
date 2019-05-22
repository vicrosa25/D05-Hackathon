
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Mensaje extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Date				momento;
	private String				texto;


	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMomento() {
		return this.momento;
	}

	public void setMomento(final Date momento) {
		this.momento = momento;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(final String texto) {
		this.texto = texto;
	}
	// Relationships ----------------------------------------------------------
	private Usuario		emisor;
	private Usuario		receptor;
	private Noticia		noticia;

	@OneToOne(optional = false)
	public Usuario getEmisor() {
		return this.emisor;
	}

	public void setEmisor(final Usuario emisor) {
		this.emisor = emisor;
	}

	@OneToOne(optional = false)
	public Usuario getReceptor() {
		return this.receptor;
	}

	public void setReceptor(final Usuario receptor) {
		this.receptor = receptor;
	}

	@ManyToOne(optional = false)
	public Noticia getNoticia() {
		return this.noticia;
	}

	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}
}
