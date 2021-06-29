package Entidades;

public class Nadador extends Atleta {
	
	private String estilo;

	
	//GETTERS AND SETTERS
	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	//CONSTRUCTOR

	public Nadador(String nome, int numero, String sexo, String estilo) {
		super(nome, numero, sexo);
		this.estilo = estilo;
	
	}

	@Override
	public String toString() {
		return super.toString() + "\nestilo = " + estilo + "\n";
	}
	
}
