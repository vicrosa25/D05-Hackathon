package datatypes;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Cartera {
	
	private String paypalEmail;
	private double saldoAcumulado;
	private double saldoAcumuladoTotal;
	
	
	@Email
	@NotNull
	@NotBlank
	@Column(unique = true)
	public String getPaypalEmail() {
		return paypalEmail;
	}
	
	
	
	public void setPaypalEmail(String paypalEmail) {
		this.paypalEmail = paypalEmail;
	}
	
	
	@Min(0)
	@NotNull
	public double getSaldoAcumulado() {
		return saldoAcumulado;
	}
	public void setSaldoAcumulado(double saldoAcumulado) {
		this.saldoAcumulado = saldoAcumulado;
	}
	
	@Min(0)
	@NotNull
	public double getSaldoAcumuladoTotal() {
		return saldoAcumuladoTotal;
	}
	public void setSaldoAcumuladoTotal(double saldoAcumuladoTotal) {
		this.saldoAcumuladoTotal = saldoAcumuladoTotal;
	}



	@Override
	public String toString() {
		return "Cartera [paypalEmail:" + paypalEmail + "]";
	}
	
	

}
