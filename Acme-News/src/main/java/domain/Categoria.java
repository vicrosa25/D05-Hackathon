package domain;

public enum Categoria {
	
	DEPORTES("Deportes"),
	ECONOMIA("Economía"),
	MOTOR("Motor"),
	CULTURA("Cultura"),
	OCIO("Ocio"),
	ESPAÑA("España"),
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
