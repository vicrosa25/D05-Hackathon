package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
 
@Entity
@Access(AccessType.PROPERTY)
public class Premio extends DomainEntity{

	private String 	nombre;
	private Integer precio;
	private String 	imagen;
	private String 	descripcion;

	// Relationships ----------------------------------------------------------
	

	// Constructor
	public Premio() {
		super();
	}

	@NotBlank
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@NotNull
	@Min(0)
	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	@URL
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	@NotBlank
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// Relationship------------------------

	

}
