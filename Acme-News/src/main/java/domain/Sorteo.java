package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
 
@Entity
@Access(AccessType.PROPERTY)
public class Sorteo extends DomainEntity{

	// Attributes
	private String 				titulo;
	private String 				descripcion;
	private Date 				fechaVencimiento;
	private Integer 			puntosNecesarios;
	private Date 				fechaInicio;
	private String 				ganador;
	

	// Relationships ----------------------------------------------------------
	private Premio premio;
	private Collection<Usuario> usuarios;
	

	@NotBlank
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@NotBlank
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	@NotNull
	//@Future
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")	
	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	@Min(0)
	public Integer getPuntosNecesarios() {
		return puntosNecesarios;
	}

	public void setPuntosNecesarios(Integer puntosNecesarios) {
		this.puntosNecesarios = puntosNecesarios;
	}
	

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	// Relationships Getters and Setters ----------------------------------------------------------
	@ManyToOne
	public Premio getPremio() {
		return premio;
	}

	public void setPremio(Premio premio) {
		this.premio = premio;
	}
	
	
	@ManyToMany
	public Collection<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public String toString() {
		return "Sorteo [titulo=" + titulo + ", descripcion=" + descripcion
				+ ", fechaVencimiento=" + fechaVencimiento
				+ ", puntosNecesarios=" + puntosNecesarios + ", fechaInicio="
				+ fechaInicio + ", ganador=" + ganador 
				+ ", premio=" + premio + "]";
	}
	
	

}
