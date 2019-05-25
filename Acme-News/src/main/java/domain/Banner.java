package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {
	
	// Attributes ----------------------------------------------------------
	/*Collection<String> imagenes;


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
	}*/
	
	private String url;

	
	@NotBlank
	@URL
	public String getUrl() {
		return url;
	}

	
	public void setUrl(String url) {
		this.url = url;
	}


	@Override
	public String toString() {
		return "Banner [url=" + url + "]";
	}
}
