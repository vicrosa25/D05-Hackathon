package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Informacion extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String titulo;
	private String imagen;
	private String descripcion;

	// Relationships ----------------------------------------------------------
	private Collection<Comentario> 	comentarios;
	private Collection<Usuario> 	usuarios;

	// Constructor
	public Informacion() {
		super();
	}

	// Getters and Setters

	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@NotNull
	@URL
	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@NotBlank
	@Type(type = "org.hibernate.type.StringClobType")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// Relationships Getters and Setters
	// ----------------------------------------------------------
	@NotNull
	@OneToMany(mappedBy = "informacion")
	public Collection<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(Collection<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	@NotNull
	@ManyToMany
	public Collection<Usuario> getUsuarios() {
		return this.usuarios;
	}
	public void setUsuarios(Collection<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}