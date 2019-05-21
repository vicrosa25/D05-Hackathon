package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Tasa extends DomainEntity {

	Double tasaVisita;
	Double tasaModerador;
	Integer puntosPrincipiante;
	Integer puntosVeterano;
	Integer puntosMaestro;
	Integer costeVeterano;
	Integer costeMaestro;

	// Relationships ----------------------------------------------------------

	// Constructor
	public Tasa() {
		super();
	}

	@DecimalMin("0.01")
	@NotNull
	public Double getTasaVisita() {
		return tasaVisita;
	}

	public void setTasaVisita(Double tasaVisita) {
		this.tasaVisita = tasaVisita;
	}

	@DecimalMin("0.01")
	@NotNull
	public Double getTasaModerador() {
		return tasaModerador;
	}

	public void setTasaModerador(Double tasaModerador) {
		this.tasaModerador = tasaModerador;
	}

	@Min(1)
	public Integer getPuntosPrincipiante() {
		return puntosPrincipiante;
	}

	public void setPuntosPrincipiante(Integer puntosPrincipiante) {
		this.puntosPrincipiante = puntosPrincipiante;
	}

	@Min(1)
	public Integer getPuntosVeterano() {
		return puntosVeterano;
	}

	public void setPuntosVeterano(Integer puntosVeterano) {
		this.puntosVeterano = puntosVeterano;
	}

	@Min(1)
	public Integer getPuntosMaestro() {
		return puntosMaestro;
	}

	public void setPuntosMaestro(Integer puntosMaestro) {
		this.puntosMaestro = puntosMaestro;
	}

	@Min(1)
	public Integer getCosteVeterano() {
		return costeVeterano;
	}

	public void setCosteVeterano(Integer costeVeterano) {
		this.costeVeterano = costeVeterano;
	}

	@Min(1)
	public Integer getCosteMaestro() {
		return costeMaestro;
	}

	public void setCosteMaestro(Integer costeMaestro) {
		this.costeMaestro = costeMaestro;
	}

	@Override
	public String toString() {
		return "Tasa [tasaVisita=" + tasaVisita + ", tasaModerador="
				+ tasaModerador + ", puntosPrincipiante=" + puntosPrincipiante
				+ ", puntosVeterano=" + puntosVeterano + ", puntosMaestro="
				+ puntosMaestro + ", costeVeterano=" + costeVeterano
				+ ", costeMaestro=" + costeMaestro + "]";
	}
}
