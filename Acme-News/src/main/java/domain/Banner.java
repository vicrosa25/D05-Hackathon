package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	Collection<String> imagenes;

	// Relationships ----------------------------------------------------------

	// Constructor
	public Banner() {
		super();
	}

	@ElementCollection
	@NotEmpty
	public Collection<String> getImagenes() {
		return imagenes;
	}

	public void setImagenes(Collection<String> imagen) {
		this.imagenes = imagen;
	}

	@Override
	public String toString() {
		return "Banner [imagen=" + imagenes + "]";
	}

}
