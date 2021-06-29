package Entidades;

public class Corredor extends Atleta {
	
	private String categoria;
	private double velocidade;
	
	//GETTERS AND SETTERS
	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public double getVelocidade() {
		return velocidade;
	}


	public void setVelocidade(double velocidade) {
		this.velocidade = velocidade;
	}

	//CONSTRUCTOR
	public Corredor(String nome, int numero, String sexo, String categoria, double velocidade) {
		super(nome, numero, sexo);
		this.categoria = categoria;
		this.velocidade = velocidade;
	}

	@Override
	public String toString() {
		return super.toString() + "Corredor: \ncategoria = " + categoria + "\nvelocidade = " + velocidade + "\n";
	}
	
}
