package br.edu.ufsc.clienttwitter.ui;

import java.awt.Color;

import javax.swing.JFrame;


public class JanelaAjuda extends JFrame {
	
	private PainelPrincipalAjuda principalPainel = new PainelPrincipalAjuda();
		
	public JanelaAjuda(){
		this.setBackground(Color.white);
		this.setTitle("Ajuda");
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(principalPainel);
		pack();
		
	}
	public void interaja() {
		this.setVisible(true);		
	}
	

}
