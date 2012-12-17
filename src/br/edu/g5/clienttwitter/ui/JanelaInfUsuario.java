package br.edu.g5.clienttwitter.ui;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JTabbedPane;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class JanelaInfUsuario extends JDialog {
	
	private PainelInfUsuario painelInfUsuario;
	private PainelListaTweetsUsuario painelTweets;
	private JTabbedPane tabPane;
	private Usuario usuario;
	private ServicosTwitter servicosTwitter;
	
	public JanelaInfUsuario(Usuario usuario, ServicosTwitter servicosTwitter){
		this.usuario = usuario;
		this.servicosTwitter = servicosTwitter;
		
		this.setBackground(Color.white);
		this.setTitle("Informações do Usuário");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setModal(true);
		
		intComponents();
		posicionaComponents();
		
		pack();
	}
	
	private void intComponents() {
		tabPane = new JTabbedPane();
		this.setContentPane(tabPane);
		painelInfUsuario = new PainelInfUsuario(usuario, servicosTwitter);
		painelTweets = new PainelListaTweetsUsuario(servicosTwitter, usuario);
		
	}

	private void posicionaComponents(){
        tabPane.add("Informações", painelInfUsuario);
        tabPane.add("Tweets", painelTweets);
	}
	
	public void mostre() {
		this.setVisible(true);		
	}

}
