
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Buscador extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String		palabra;
	private Date		fechaInicio;
	private Date		fechaFin;
	private Date		ultimaActualizacion;
	private Categoria	categoria;


	public String getPalabra() {
		return this.palabra;
	}

	public void setPalabra(final String palabra) {
		this.palabra = palabra;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(final Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(final Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getUltimaActualizacion() {
		return this.ultimaActualizacion;
	}
	public void setUltimaActualizacion(final Date ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
	}

	@Valid
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}

	// Relationships -------------------------------------------------------------
	private Collection<Noticia>	noticias;

	@Valid
	@ManyToMany
	public Collection<Noticia> getPositions() {
		return this.noticias;
	}

	public void setPositions(final Collection<Noticia> noticias) {
		this.noticias = noticias;
	}
}
