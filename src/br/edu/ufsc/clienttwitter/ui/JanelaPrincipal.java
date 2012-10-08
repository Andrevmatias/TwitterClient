package br.edu.ufsc.clienttwitter.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;

@SuppressWarnings("serial")
public class JanelaPrincipal extends JFrame {

	private TwitterInterface twitterInterface;
	
	private PainelLogin painelLogin;
	private PainelTweets painelTweets;

	public JanelaPrincipal(final TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		
		this.setTitle("Twitter Client");
		this.setSize(250, 800);
		this.setLocationRelativeTo(null);
		this.setExtendedState(MAXIMIZED_VERT);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		painelLogin = new PainelLogin(twitterInterface);
		final JFrame janela = this;
		painelLogin.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent e) {
				super.componentHidden(e);
				
				painelTweets = new PainelTweets(twitterInterface);
				janela.setContentPane(painelTweets);
			}
		});
		this.setContentPane(painelLogin);
	}

	public void interaja() {
		this.setVisible(true);
	}
}
