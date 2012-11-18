package br.edu.g5.clienttwitter.ui.ajuda;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class PainelSobre extends JScrollPane{

	private JLabel sobre;
	
	public PainelSobre() {
		this.setName("Sobre");
		this.getViewport().setBackground(Color.white);
		
		iniciaComponents();
		posicionaComponents();
	}

	private void posicionaComponents() {
		this.getViewport().add(sobre);
	}

	private void iniciaComponents() {
		sobre = new JLabel(gereSobreHTML());
	}

	private String gereSobreHTML() {
		StringBuilder builderSobre = new StringBuilder();
		builderSobre
			.append("<html>")
			.append("<div style=\"width: 300px; margin: 10px;\">")
			
			.append("<h1>Sobre</h1>")
			
			.append("<h4>Versão: 2.0</h4>")
			
			.append("<h4>Criadores</h4>")
			.append("<ul>")
			.append("<li>André V. Matias</li>")
			.append("<li>Christian Zirke</li>")
			.append("<li>Paulo Ricardo S. Machado</li>")
			.append("</ul>")
		
			.append("<h4>Contato</h4>")
			.append("clientetwitter@gmail.com")
			
			.append("</div>");

		return builderSobre.toString();
	}

	
}
