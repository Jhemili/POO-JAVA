
public class Saltador extends Atleta{
	
	private double altura;
	
	
	//GETTERS AND SETTERS
	public double getAltura() {
		return altura;
	}


	public void setAltura(double altura) {
		this.altura = altura;
	}

	//CONSTRUCTOR
	public Saltador(String nome, int numero, String sexo, double altura) {
		super(nome, numero, sexo);
		this.altura = altura;
	}


	
	

	
}
