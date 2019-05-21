package forms;

import security.UserAccount;

public class UsuarioForm {
	
	private UserAccount userAccount;
	private String passwordCheck;
	private String nombre;
	private String apellidos;
	private String email;
	private boolean accepted;
	

	// Getters and Setters
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	
	
	
	
	// to string
	@Override
	public String toString() {
		return "UsuarioForm [userAccount=" + userAccount + ", passwordCheck=" + passwordCheck + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", email=" + email + ", accepted=" + accepted + "]";
	}
	
	

}
