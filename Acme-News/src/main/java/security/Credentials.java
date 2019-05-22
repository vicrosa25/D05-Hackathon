/*
 * Credentials.java
 */

package security;

import javax.validation.constraints.Size;

public class Credentials {

	// Constructors -----------------------------------------------------------

	public Credentials() {
		super();
	}


	// Attributes -------------------------------------------------------------

	private String	username;
	private String	password;


	@Size(min = 5, max = 32)
	public String getUsername() {
		return this.username;
	}

	public void setJ_username(final String username) {
		this.username = username;
	}

	@Size(min = 5, max = 32)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

}
