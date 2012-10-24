package br.edu.ufsc.clienttwitter.ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class paineiSobre extends JPanel{

	private JLabel sobre;
	
	public paineiSobre() {
	this.setName("Sobre");
	this.setBackground(Color.white);
	
	iniciaComponents();
	posicionaComponents();

}

	private void posicionaComponents() {
		this.add(sobre);
	}

	private void iniciaComponents() {
		sobre = new JLabel("<html> Versão: 1.0<br><br>" +
				" Criadores:<br>"+ "Andre V. Matias<br>" +
				"Christian Zirke<br>" +
				"Paulo Ricardo S. Machado<br><br>" +
				"Contato:<br> clientetwitter@gmail.com </html>");
		
	}

	
}
