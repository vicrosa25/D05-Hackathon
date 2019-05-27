package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Access(AccessType.PROPERTY)
public class Noticia extends Informacion {


	// Attributes -------------------------------------------------------------
	private String 				video;
	private Categoria 			categoria;
	private Estado 				estado;
	private Long 				numeroVisitas;
	private Date 				fecha;
	private Boolean				isBanned;

	//  -------------------------------------------------------------

	private Collection<Noticia> noticiasRelacionadas;
	private Collection<Reporte> reportes;
	private Periodista 			periodista;


	// Constructor ----------------------------------------------------------
	public Noticia(){
		super();
	}

	//  ----------------------------------------------------------

	@URL
	public String getVideo() {
		return this.video;
	}
	public void setVideo(String video) {
		this.video = video;
	}

	@NotNull
	public Categoria getCategoria() {
		return this.categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	//
	@NotNull
	public Estado getEstado() {
		return this.estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getFecha() {
		return this.fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Min(0)
	public Long getNumeroVisitas(){
		return this.numeroVisitas;
	}
	public void setNumeroVisitas(Long num){
		this.numeroVisitas=num;
	}
	
	public Boolean getIsBanned() {
		return isBanned;
	}

	
	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}
	
	
	


	//----------------------------------------
	@NotNull
	@ManyToMany
	public Collection<Noticia> getNoticiasRelacionadas() {
		return this.noticiasRelacionadas;
	}
	public void setNoticiasRelacionadas(Collection<Noticia> noticias) {
		this.noticiasRelacionadas = noticias;
	}
	@NotNull
	@OneToMany(mappedBy="noticia")
	public Collection<Reporte> getReportes(){
		return this.reportes;
	}
	public void setReportes(Collection<Reporte> reportes){
		this.reportes=reportes;
	}

	@ManyToOne(optional=false)
	public Periodista getPeriodista() {
		return this.periodista;
	}

	public void setPeriodista(Periodista periodista) {
		this.periodista = periodista;
	}


	public String toString(){
		return "Noticia: "+this.getTitulo();
	}
}