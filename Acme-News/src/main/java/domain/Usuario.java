
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Usuario extends Actor {

	private Integer					puntos;
	private Estatus					estatus;
	private boolean					banned;
	private String 					usernameCopyForBan;
	
	// --------------------------------------------------
	private Collection<Usuario>		seguidores;
	private Collection<Usuario>		siguiendo;
	private Collection<Comentario>	comentarios;
	private Collection<Reporte>		reportes;
	private Collection<Informacion>	informacionCompartida;
	private Collection<Sorteo>		sorteos;


	// Constructors -----------------------------------------------------------

	public Usuario() {
		super();
	}
	//  -----------------------------------------------------------
	
	@NotNull
	public boolean getBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public String getUsernameCopyForBan() {
		return usernameCopyForBan;
	}

	public void setUsernameCopyForBan(String usernameCopyForBan) {
		this.usernameCopyForBan = usernameCopyForBan;
	}

	@NotNull
	@Min(0)
	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	@NotNull
	public Estatus getEstatus() {
		return estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}
	
	//  -----------------------------------------------------------
	@NotNull
	@ManyToMany(mappedBy="siguiendo")
	public Collection<Usuario> getSeguidores() {
		return this.seguidores;
	}
	



	public void setSeguidores(Collection<Usuario> seguidores) {
		this.seguidores = seguidores;
	}
	@NotNull
	@ManyToMany
	public Collection<Usuario> getSiguiendo() {
		return this.siguiendo;
	}
	public void setSiguiendo(Collection<Usuario> siguiendo) {
		this.siguiendo = siguiendo;
	}
	
	@NotNull
	@OneToMany(mappedBy="usuario")
	public Collection<Comentario> getComentarios(){
		return this.comentarios;
	}
	public void setComentarios(Collection<Comentario> comentarios){
		this.comentarios=comentarios;
	}
	
	
	@NotNull
	@OneToMany(mappedBy="usuario")
	public Collection<Reporte> getReportes(){
		return this.reportes;
	}
	public void setReportes(Collection<Reporte> reportes){
		this.reportes=reportes;
	}
	
	@ManyToMany(mappedBy="usuarios")
	public Collection<Informacion> getInformacionCompartida(){
		return this.informacionCompartida;
	}
	public void setInformacionCompartida(Collection<Informacion> informacionCompartida){
		this.informacionCompartida=informacionCompartida;
	}
	
	@ManyToMany(mappedBy="usuarios")
	public Collection<Sorteo> getSorteos(){
		return this.sorteos;
	}
	public void setSorteos(Collection<Sorteo> sorteos){
		this.sorteos=sorteos;
	}
}