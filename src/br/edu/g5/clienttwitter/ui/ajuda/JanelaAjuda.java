package br.edu.g5.clienttwitter.ui.ajuda;

import java.awt.Color;

import javax.swing.JDialog;


public class JanelaAjuda extends JDialog {
	
	private PainelPrincipalAjuda principalPainel = new PainelPrincipalAjuda();
		
	public JanelaAjuda(){
		this.setBackground(Color.white);
		this.setTitle("Ajuda");
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(principalPainel);
	}
	public void interaja() {
		this.setVisible(true);		
	}
	

}
