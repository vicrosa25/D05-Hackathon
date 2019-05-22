package forms;

import domain.Usuario;

public class EnviarNoticiaForm {

	private int noticiaId;
	private Usuario usuario;
	private String texto;


	// Getters and Setters
	public int getNoticiaId(){
		return this.noticiaId;
	}
	public void setNoticiaId(int noticiaId){
		this.noticiaId=noticiaId;
	}

	public Usuario getUsuario(){
		return this.usuario;
	}
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	public String getTexto() {
		return this.texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
}
