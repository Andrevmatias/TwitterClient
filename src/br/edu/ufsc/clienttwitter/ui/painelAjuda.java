package br.edu.ufsc.clienttwitter.ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class painelAjuda extends JPanel{
	
	private JLabel textoAjuda;

	public painelAjuda() {
	this.setName("Ajuda");
	this.setBackground(Color.white);
	iniciaComponents();
	posicionaComponents();

}

	private void posicionaComponents() {
		this.add(textoAjuda);
		}

	private void iniciaComponents() {
		textoAjuda = new JLabel("<html> Como entrar:<br> Para acessar sua conta pelo The Passarinho � necess�rio gerar um c�digo." +
				" Ap�s gerado, insira no campo e clique em entrar.<br><br>" +
				" Como gerar o c�digo:<br> " +
				"Para gerar o c�digo basta clicar no link 'Gerar c�digo para acesso' e seguir os passos no site.<br><br>" +
				" Como Twittar:<br>" +
				"Para twittar basta inserir o texto no campo inferior e apertar ENTER. Vale lembrar que o limite de caracteres � 140.<br><br>" +
				" Como Retwittar:<br> " +
				"Para retwittar basta clicar com o bot�o direito do mouse no tweet desejado e selecionar a op��o 'Retwittar'.<br><br>" +
				"Lista de Tweets:<br>" +
				"Caso desejado ver tweets antigos basta rolar o scroll da painel de tweets at� o fim, tweets antigos ir�o ser carregados.<br></html>");
	}

}
