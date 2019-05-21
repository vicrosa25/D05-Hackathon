package domain;

public enum Categoria {
	
	DEPORTES("Deportes"),
	ECONOMIA("Econom�a"),
	MOTOR("Motor"),
	CULTURA("Cultura"),
	OCIO("Ocio"),
	ESPA�A("Espa�a"),
	INTERNACIONAL("Internacional"),
	JUEGOS("Juegos"),
	OTROS("Otros");
	
	private String label;
	
	private Categoria(String label){
		this.label = label;
	}
	
	public String getLabel(){
		return this.label;
	}

}
