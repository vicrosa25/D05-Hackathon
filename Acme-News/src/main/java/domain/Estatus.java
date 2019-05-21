package domain;

public enum Estatus {
	
	PRINCIPIANTE("Principiante"),
	VETERANO("Veterano"),
	MAESTRO("Maestro");
	
	private String label;
	
	private Estatus(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return this.label;
	}

}
