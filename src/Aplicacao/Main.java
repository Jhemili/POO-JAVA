package Aplicacao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Entidades.Atleta;
import Entidades.Corredor;
import Entidades.Nadador;
import Entidades.Saltador;

public class Main {

	private ArrayList<Atleta> atletas = new ArrayList<Atleta>();
	
	public String[] leValores (String [] dadosIn){
		String [] dadosOut = new String [dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog  ("Entre com " + dadosIn[i]+ ": ");

		return dadosOut;
	}

	public Nadador leNadador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Numero","Sexo","Estilo"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);

		Nadador nadador= new Nadador (valores[0],numero,valores[2],valores[3]);
		return nadador;
	}

	public Saltador leSaltador (){

		String [] valores = new String [3];
		String [] nomeVal = {"Nome", "Numero", "Sexo","Altura"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);
		double altura = this.retornaDouble(valores[3]);

				Saltador saltador = new Saltador (valores[0],numero,valores[2],altura);
				return saltador;
	}
	
	public Corredor leCorredor (){

		String [] valores = new String [4];
		String [] nomeVal = {"Nome", "Numero", "Sexo","Categoria","Velocidade"};
		valores = leValores (nomeVal);

		int numero = this.retornaInteiro(valores[1]);
		double velocidade = this.retornaDouble(valores[3]);

				Corredor corredor = new Corredor (valores[0],numero,valores[2],valores[3],velocidade);
				return corredor;
	}


	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // M�todo est�tico, que tenta tranformar uma string em inteiro
			return true;
		} catch (NumberFormatException e) { // N�o conseguiu tranformar em inteiro e gera erro
			return false;
		}
	}
	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;

		//Enquanto n�o for poss�vel converter o valor de entrada para inteiro, permanece no loop
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}
	

	private boolean doubleValido(String s) {
		try {
			Double.parseDouble(s); // Método estático, que tenta tranformar uma string em double
			return true;
		} catch (NumberFormatException e) { // Não conseguiu tranformar em double e gera erro
			return false;
		}
	}
	public double retornaDouble(String entrada) { // retorna um valor inteiro
		float numFloat;

		//Enquanto não for possível converter o valor de entrada para FLOAT, permanece no loop
		while (!this.doubleValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número decimal.");
		}
		return Double.parseDouble(entrada);
	}

	public void salvaAtletas (ArrayList<Atleta> atletas){
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream 
					(new FileOutputStream("atletas.dados"));
			for (int i=0; i < atletas.size(); i++)
				outputStream.writeObject(atletas.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectOutputStream
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Atleta> recuperaAtletas (){
		ArrayList<Atleta> atletasTemp = new ArrayList<Atleta>();

		ObjectInputStream inputStream = null;

		try {	
			inputStream = new ObjectInputStream
					(new FileInputStream("atletas.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Atleta) {
					atletasTemp.add((Atleta) obj);
				}   
			}          
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,"Arquivo com atletas não existe!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {  //Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return atletasTemp;
		}
	}

	public void menuAtletas (){

		String menu = "";
		String entrada;
		int    opc1, opc2;

		do {
			menu = "Controle Atletas\n" +
					"Opções:\n" + 
					"1. Entrar Atleta\n" +
					"2. Exibir Atletas\n" +
					"3. Limpar Atletas\n" +
					"4. Gravar Atleta\n" +
					"5. Recuperar Atletas\n" +
					"9. Sair";
			entrada = JOptionPane.showInputDialog (menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrar dados
				menu = "Entrada de Atletas\n" +
						"Opções:\n" + 
						"1. Nadador\n" +
						"2. Corredor\n" +
						"3. Saltador";

				entrada = JOptionPane.showInputDialog (menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2){
				case 1: atletas.add((Atleta)leNadador());
				break;
				case 2: atletas.add((Atleta)leCorredor());
				break;
				case 3: atletas.add((Atleta)leSaltador());
				break;
				default: 
					JOptionPane.showMessageDialog(null,"Atleta NÃO escolhido!");
				}

				break;
				
			case 2: // Exibir dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Não há atletas em memória. Entre com atletas primeiramente");
					break;
				}
				String dados = "";
				for (int i=0; i < atletas.size(); i++)	{
					dados += atletas.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null,dados);
				break;
				
			case 3: // Limpar Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Não há atletas em memória. Entre com atletas primeiramente");
					break;
				}
				atletas.clear();
				JOptionPane.showMessageDialog(null,"Dados LIMPOS com sucesso!");
				break;
				
			case 4: // Grava Dados
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Não há atletas em memória. Entre com atletas primeiramente");
					break;
				}
				salvaAtletas(atletas);
				JOptionPane.showMessageDialog(null,"Dados SALVOS com sucesso!");
				break;
				
			case 5: // Recupera Dados
				atletas = recuperaAtletas();
				if (atletas.size() == 0) {
					JOptionPane.showMessageDialog(null,"Sem dados para apresentar.");
					break;
				}
				JOptionPane.showMessageDialog(null,"Dados RECUPERADOS com sucesso!");
				break;
			case 9:
				JOptionPane.showMessageDialog(null,"Fim do aplicativo CONTROLE ATLETAS");
				break;
			}
		} while (opc1 != 9);
	}
	
	public static void main(String[] args) {
		
		Main at = new Main();
		at.menuAtletas();
		

	}

}
