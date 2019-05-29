package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;

@Entity
@Access(AccessType.PROPERTY)
public class Agencia extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String 		titulo;
	private String 		direccion;
	private Integer 	capacidadDisponible;
	private double 		tasa;
	private Long 		importancia;

	// Relationships ----------------------------------------------------------
	private Collection<Periodista> 	periodistas;
	private Collection<Evento> 		eventos;
	private Manager 				manager;

	// Constructor
	public Agencia() {
		super();
	}

	// Getters and Setters
	// ----------------------------------------------------------

	@NotBlank
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@NotBlank
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@NotNull
	@Min(0)
	public Integer getCapacidadDisponible() {
		return this.capacidadDisponible;
	}

	public void setCapacidadDisponible(Integer capacidadDisponible) {
		this.capacidadDisponible = capacidadDisponible;
	}

	@NotNull
	@Min(0)
	public double getTasa() {
		return this.tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	@Transient
	public Long getImportancia() {
		Long result = (long) 0;
		if (this.periodistas != null) {
			for (Periodista periodista : this.periodistas) {
				for (Noticia noticia : periodista.getNoticias()){
					result = result + noticia.getNumeroVisitas();
				}
			}
		}
		this.importancia = result;
		return this.importancia;
	}

	public void setImportancia(Long importancia) {
		this.importancia = importancia;
	}

	// Relationships Getters and Setters
	// ----------------------------------------------------------

	@OneToMany
	public Collection<Periodista> getPeriodistas() {
		return this.periodistas;
	}

	public void setPeriodistas(Collection<Periodista> periodistas) {
		this.periodistas = periodistas;
	}

	@ManyToOne(optional = true)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void addPeriodista(Periodista periodista) {
		this.periodistas.add(periodista);
	}

	public void removePeriodistas(Periodista periodista) {
		this.periodistas.remove(periodista);
	}


	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Evento> getEventos() {
		return this.eventos;
	}
	public void setEventos(Collection<Evento> eventos) {
		this.eventos = eventos;
	}

}
