package br.edu.ufsc.clienttwitter.ui;

import javax.swing.JFrame;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.enums.Paineis;

public class JanelaPrincipal extends JFrame {

	private TwitterInterface twitterInterface;
	
	private PainelLogin painelLogin;

	public JanelaPrincipal(TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		
		setTitle("The Passarinho");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		initComponents();
		
		pack();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		painelLogin = new PainelLogin(this, twitterInterface);
		this.setContentPane(painelLogin);
	}

	public void interaja() {
		this.setVisible(true);
	}

	public void mostre(Paineis tweets) {
		switch(tweets){
		case Tweets: 
			this.setContentPane(new PainelTweets(twitterInterface));
			break;
		case Login:
			this.setContentPane(new PainelLogin(this, twitterInterface));
			break;
		}
		pack();
		setLocationRelativeTo(null);
	}
}
