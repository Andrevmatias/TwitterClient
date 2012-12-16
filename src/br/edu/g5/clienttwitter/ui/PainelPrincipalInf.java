package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class PainelPrincipalInf extends JPanel {
	
	private CardLayout cardLayout = new CardLayout();
	private JButton informacoes, tweets;
	private PainelInfUsuario painelInfUsuario, painelListaTweets;
	private JPanel painelCard = new JPanel();
	private JPanel painelbtns;
	private Usuario usuario;
	
	public PainelPrincipalInf(Usuario usuario, ServicosTwitter servicosTwitter){
		this.setBackground(Color.white);
		this.usuario = usuario;
		painelInfUsuario = new PainelInfUsuario(usuario, servicosTwitter);
		posicionaComponents();
	}
	
	public void posicionaComponents(){
        this.setLayout(new BorderLayout());
        informacoes = new JButton("Informações sobre " + "@" + usuario.getNick());
        informacoes.addActionListener(new ActionListener() {
			
        	@Override
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(painelCard, "painelInfUsuario");
			}
		});
        tweets = new JButton("Mostrar Tweets");
        tweets.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(painelCard, "painelListaTweets");
			}
		});
        painelbtns = new JPanel();
        painelbtns.setLayout(new BoxLayout(painelbtns, BoxLayout.PAGE_AXIS));
        painelbtns.add(informacoes);
        painelbtns.add(tweets);
        this.add(painelbtns, BorderLayout.LINE_START);
              
        painelCard.setLayout(cardLayout);
        painelCard.add(painelInfUsuario, "painelInfUsuario");
        this.add(painelCard, BorderLayout.CENTER);
	}


}
