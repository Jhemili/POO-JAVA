package Entidades;

import java.io.Serializable;

public abstract class Atleta implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private int numero;
	private String sexo;
		
	//GETTERS AND SETTERS
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	//CONSTRUCTOR 
	public Atleta() {
		super();
	}
	
	public Atleta(String nome, int numero, String sexo) {
		super();
		this.nome = nome;
		this.numero = numero;
		this.sexo = sexo;
	}
	
	@Override
	public String toString() {
		return "Atleta:\nNome = " + nome + "\nnumero = " + numero + "\nsexo = " + sexo + "\n";
	}
		
	
}
