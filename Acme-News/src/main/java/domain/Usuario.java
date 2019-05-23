
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
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
	private Collection<Periodista>	periodistas;
	private Collection<Comentario>	comentarios;
	private Collection<Reporte>		reportes;
	private Collection<Informacion>	informacionCompartida;
	private Collection<Sorteo>		sorteos;
	private Buscador				buscador;


	// Constructors -----------------------------------------------------------

	public Usuario() {
		super();
	}
	//  -----------------------------------------------------------

	@NotNull
	public boolean getBanned() {
		return this.banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public String getUsernameCopyForBan() {
		return this.usernameCopyForBan;
	}

	public void setUsernameCopyForBan(String usernameCopyForBan) {
		this.usernameCopyForBan = usernameCopyForBan;
	}

	@NotNull
	@Min(0)
	public Integer getPuntos() {
		return this.puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	@NotNull
	public Estatus getEstatus() {
		return this.estatus;
	}

	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	//  -----------------------------------------------------------
	@NotNull
	@ManyToMany
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

	@ManyToMany
	public Collection<Periodista> getPeriodistas() {
		return this.periodistas;
	}
	public void setPeriodistas(Collection<Periodista> periodistas) {
		this.periodistas = periodistas;
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

	@ManyToMany
	public Collection<Informacion> getInformacionCompartida(){
		return this.informacionCompartida;
	}
	public void setInformacionCompartida(Collection<Informacion> informacionCompartida){
		this.informacionCompartida=informacionCompartida;
	}

	@ManyToMany
	public Collection<Sorteo> getSorteos(){
		return this.sorteos;
	}
	public void setSorteos(Collection<Sorteo> sorteos){
		this.sorteos=sorteos;
	}

	@NotNull
	@Valid
	@OneToOne(optional = false)
	public Buscador getBuscador() {
		return this.buscador;
	}

	public void setBuscador(final Buscador buscador) {
		this.buscador = buscador;
	}
}