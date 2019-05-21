package domain;

public enum Estado {
	
	
	DENEGADA("Denegada"),
	PENDIENTE("Pendiente"),
	PUBLICADA("Publicada");
	
	
	private String label;
	
	private Estado(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return this.label;
	}

}
