package br.edu.g5.clienttwitter.ui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class JanelaInfUsuario extends JDialog {
	
	private PainelPrincipalInf painel;
	
	public JanelaInfUsuario(Usuario usuario, ServicosTwitter servicosTwitter){
		painel = new PainelPrincipalInf(usuario, servicosTwitter);
		this.setBackground(Color.white);
		this.setTitle("Informações do Usuário");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(painel);
		this.setModal(true);
		pack();
	}
	
	public void mostre() {
		this.setVisible(true);		
	}

}
