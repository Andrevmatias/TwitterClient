package br.edu.g5.clienttwitter.ui.ajuda;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class PainelAjuda extends JScrollPane{
	
	private JLabel textoAjuda;

	public PainelAjuda() {
		this.setName("Ajuda");
		this.setSize(300, 200);
		this.getViewport().setBackground(Color.white);
		iniciaComponents();
		posicionaComponents();

	}

	private void posicionaComponents() {
		this.getViewport().add(textoAjuda);
	}

	private void iniciaComponents() {
		textoAjuda = new JLabel(gereAjudaHTML());
	}

	private String gereAjudaHTML() {
		StringBuilder builderAjuda = new StringBuilder();
		builderAjuda
			.append("<html>")
			.append("<div style=\"width: 300px; margin: 10px;\">")
			
			.append("<h1>Ajuda</h1>")
			
			.append("<p>")
			.append("<h2>Como entrar</h2>")
			.append("Para acessar sua conta pelo The Passarinho " +
					"é necessário gerar um código. Após gerado, " +
					"insira no campo e clique em entrar.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como gerar o código</h2>")
			.append("Para gerar o código basta clicar no link " +
					"'Gerar código para acesso' e seguir os " +
					"passos no site.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como Twittar</h2>")
			.append("Para twittar basta inserir o texto no campo" +
					" inferior e apertar ENTER. Vale lembrar que " +
					"o limite de caracteres é 140.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como Retwittar</h2>")
			.append("Para retwittar basta clicar com o botão direito " +
					"do mouse no tweet desejado e selecionar a " +
					"opção 'Retwittar'.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Lista de Tweets</h2>")
			.append("Caso deseje ver tweets antigos basta rolar o scroll " +
					"do painel de tweets até o fim e seus tweets antigos " +
					"irão ser carregados.")
			.append("</p>")
			
			.append("</div>");
		
		return builderAjuda.toString();
	}

}
