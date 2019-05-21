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

	String titulo;
	String descripcion;
	Date fechaVencimiento;
	Integer puntosNecesarios;
	Date fechaInicio;
	String ganador;
	Collection<Usuario> usuarios;

	// Relationships ----------------------------------------------------------

	Premio premio;

	// Constructor
	public Sorteo() {
		super();
	}

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
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")	
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


	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")	
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getGanador() {
		return ganador;
	}

	public void setGanador(String ganador) {
		this.ganador = ganador;
	}

	// Relationships Getters and Setters
	// ----------------------------------------------------------





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
