package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {
	
	
	
	// Relationships ----------------------------------------------------------
	private Collection<Agencia> agencias;
	
	
	// Constructor
	public Manager(){
		super();
	}

	
	// Relationships    Getters and Setters	----------------------------------------------------------
	
	@OneToMany
	public Collection<Agencia> getAgencias() {
		return agencias;
	}


	public void setAgencias(Collection<Agencia> agencias) {
		this.agencias = agencias;
	}


	public void removeAgencia(Agencia agencia) {
		this.agencias.remove(agencia);
	}
	
	
}
