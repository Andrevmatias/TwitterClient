package br.edu.ufsc.clienttwitter.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.enums.Paineis;

@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame {

	private TwitterInterface twitterInterface;
	
	private PainelLogin painelLogin;
	private PainelTweets painelTweets;

	public JanelaPrincipal(final TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		
		this.setTitle("The Passarinho");
		this.setSize(250, 800);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_VERT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		painelLogin = new PainelLogin(this, twitterInterface);
		final JFrame janela = this;
		this.setContentPane(painelLogin);
	}

	public void interaja() {
		this.setVisible(true);
	}

	public void mostre(Paineis tweets) {
		switch(tweets){
			case Tweets: this.setContentPane(painelTweets);
		}
	}
}
