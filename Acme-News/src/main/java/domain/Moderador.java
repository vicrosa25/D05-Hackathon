package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import datatypes.Cartera;

@Entity
@Access(AccessType.PROPERTY)
public class Moderador extends Actor {

	// Relationships ----------------------------------------------------------
	private Cartera cartera;

	@Valid
	@NotNull
	public Cartera getCartera() {
		return cartera;
	}

	public void setCartera(Cartera cartera) {
		this.cartera = cartera;
	}

	@Override
	public String toString() {
		return "Moderador [cartera=" + cartera + ", getName()=" + getNombre()
				+ ", getSurname()=" + getApellidos() + ", getEmail()="
				+ getEmail() + ", getUserAccount()=" + getUserAccount()
				+ ", toString()=" + super.toString() + ", getId()=" + getId()
				+ ", getVersion()=" + getVersion() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + "]";
	}
}
