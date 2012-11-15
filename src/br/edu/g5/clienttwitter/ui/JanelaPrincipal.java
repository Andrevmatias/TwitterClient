package br.edu.g5.clienttwitter.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.ui.ajuda.JanelaAjuda;
import br.edu.g5.clienttwitter.ui.enums.Paineis;

public class JanelaPrincipal extends JFrame {

	private JMenuBar barra;
	private JMenu menu;
	private JMenuItem menuItemAjuda;
	private JMenuItem menuItemSair;
	private ServicosTwitter servicosTwiter;

	private PainelLogin painelLogin;
	private JMenuItem menuItemPesquisaUsuarios;
	private JMenuItem menuItemPesquisaTweets;

	public JanelaPrincipal(ServicosTwitter twitterInterface) {
		this.servicosTwiter = twitterInterface;

		setTitle("The Passarinho");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initComponents();

		pack();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		barra = new JMenuBar();
		menu = new JMenu("Menu");
		menuItemPesquisaTweets = new JMenuItem("Pesquisar Tweets");
		menuItemPesquisaUsuarios = new JMenuItem("Pesquisar usuários");
		menuItemAjuda = new JMenuItem("Ajuda");
		menuItemSair = new JMenuItem("Sair");

		menuItemPesquisaTweets.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostre(Paineis.PESQUISA_TWEETS);
				
			}
		});
		
		menuItemPesquisaUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostre(Paineis.PESQUISA_USUARIOS);
			}
		});
		
		menuItemAjuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JanelaAjuda janela = new JanelaAjuda();
				janela.interaja();
			}
		});
		
		menuItemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		
		menu.add(menuItemPesquisaTweets);
		menu.add(menuItemPesquisaUsuarios);
		menu.add(menuItemAjuda);
		menu.add(menuItemSair);
		barra.add(menu);
		setJMenuBar(barra);
		
		painelLogin = new PainelLogin(this, servicosTwiter);
		this.setContentPane(painelLogin);
	}

	public void interaja() {
		this.setVisible(true);
	}

	public void mostre(Paineis tweets) {
		switch (tweets) {
		case TWEETS:
			this.setContentPane(new PainelTweets(servicosTwiter));
			break;
		case LOGIN:
			this.setContentPane(new PainelLogin(this, servicosTwiter));
			break;
		case PESQUISA_TWEETS:
			this.setContentPane(new PainelPesquisaTweets(servicosTwiter));
			break;
		case PESQUISA_USUARIOS:
			this.setContentPane(new PainelPesquisaUsuarios(servicosTwiter));
			break;
		}
		pack();
		setLocationRelativeTo(null);
	}
}
