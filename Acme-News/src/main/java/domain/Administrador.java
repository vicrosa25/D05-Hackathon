package domain;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Administrador extends Actor {

	// Relationships ----------------------------------------------------------


	// Constructor
	public Administrador() {
		super();
	}

	// Relationships Getters and Setters
	// ----------------------------------------------------------

	
	@Override
	public String toString() {
		return "Administrador [ getName()=" + getNombre()
				+ ", getSurname()=" + getApellidos() + ", getEmail()="
				+ getEmail() + ", getUserAccount()=" + getUserAccount()
				+ ", toString()=" + super.toString() + ", getId()=" + getId()
				+ ", getVersion()=" + getVersion() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + "]";
	}

	
}
