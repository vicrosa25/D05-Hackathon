package utilities;

import org.hibernate.validator.constraints.NotBlank;

public class Search {

	public String word;
	
	public Search(String word){
		this.word = word;
	}
	public Search(){
		super();
	}
	@NotBlank
	public String getWord(){
		return this.word;
	}
	public void setWord(String word){
		this.word=word;
	}
}
