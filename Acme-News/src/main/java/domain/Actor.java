package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	// Attributes
	private String		nombre;
	private String		apellidos;
	private String		email;

	// Relationships
	private UserAccount	userAccount;


	

	public Actor() {
		super();

	}

	/********************* Attributes getters and setters *****************************************************************/

	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Email
	@NotBlank
	@Column(unique = true)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	/********************* Relation getters and setters *****************************************************************/

	@Valid
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	/********************* Generic methods ********************************************************************************/

	@Override
	public String toString() {
		return "Actor [name=" + this.nombre + ", surname=" + this.apellidos + ", email=" + this.email + ", userAccount=" + this.userAccount +  "]";
	}
}
