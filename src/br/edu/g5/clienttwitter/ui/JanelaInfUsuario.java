package br.edu.g5.clienttwitter.ui;

import java.awt.Color;

import javax.swing.JDialog;

import br.edu.g5.clienttwitter.logic.Usuario;

public class JanelaInfUsuario extends JDialog {
	
	private PainelInfUsuario painel;
	
	public JanelaInfUsuario(Usuario usuario){
		painel = new PainelInfUsuario(usuario);
		this.setBackground(Color.white);
		this.setTitle("Informações do Usuario");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(painel);
	}
	
	public void mostre() {
		this.setVisible(true);		
	}

}
