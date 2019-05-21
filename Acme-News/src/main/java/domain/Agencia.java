package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
	private String titulo;
	private String direccion;
	private int capacidadDisponible;
	private double tasa;
	@SuppressWarnings("unused")
	private Long importancia;

	// Relationships ----------------------------------------------------------
	private Collection<Periodista> periodistas;
	private Manager manager;

	// Constructor
	public Agencia() {
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
	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Min(0)
	public int getCapacidadDisponible() {
		return capacidadDisponible;
	}

	public void setCapacidadDisponible(int capacidadDisponible) {
		this.capacidadDisponible = capacidadDisponible;
	}

	@NotNull
	@Min(0)
	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	@Transient
	public Long getImportancia() {
		Long result = (long) 0;
		if (periodistas != null) {
			for (Periodista periodista : periodistas) {
				for (Informacion infor : periodista.getInformacion())
					if (infor.getClass().equals(Noticia.class)) {
						Long visitas = ((Noticia) infor).getNumeroVisitas();
						result = result + visitas;
					}
			}
		}
		return result;
	}

	public void setImportancia(Long importancia) {
		this.importancia = importancia;
	}

	// Relationships Getters and Setters
	// ----------------------------------------------------------

	@OneToMany
	public Collection<Periodista> getPeriodistas() {
		return periodistas;
	}

	public void setPeriodistas(Collection<Periodista> periodistas) {
		this.periodistas = periodistas;
	}

	@ManyToOne(optional = true)
	public Manager getManager() {
		return manager;
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

}
