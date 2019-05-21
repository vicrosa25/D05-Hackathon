package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comentario extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String titulo;
	private String descripcion;
	private Date fecha;

	// Relationships ----------------------------------------------------------
	private Informacion informacion;
	private Usuario usuario;

	// Constructor -----------------------------------------------------
	public Comentario() {
		super();
	}

	// Getters and Setters
	// ----------------------------------------------------------

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
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	// Relationships Getters and Setters
	// ----------------------------------------------------------

	@ManyToOne(optional = false)
	public Informacion getInformacion() {
		return informacion;
	}

	public void setInformacion(Informacion informacion) {
		this.informacion = informacion;
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