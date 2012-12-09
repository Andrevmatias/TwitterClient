package br.edu.g5.clienttwitter.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.edu.g5.clienttwitter.logic.ServicosAuntent;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.ui.ajuda.JanelaAjuda;
import br.edu.g5.clienttwitter.ui.enums.Paineis;

public class JanelaPrincipal extends JFrame {

	private JMenuBar barra;
	private JMenu menu;
	private JMenuItem menuItemAjuda;
	private JMenuItem menuItemSair;
	private ServicosTwitter servicosTwiter;
	private ServicosAuntent servicosAutent;
	private PainelLogin painelLogin;
	private JMenuItem menuItemPesquisaUsuarios;
	private JMenuItem menuItemPesquisaTweets;
	private JMenuItem menuItemPaginaInicial;
	private JMenuItem menuItemSeguidores;

	public JanelaPrincipal(ServicosTwitter twitterInterface, ServicosAuntent twitterAutent) {
		this.servicosTwiter = twitterInterface;
		this.servicosAutent = twitterAutent;

		setTitle("The Passarinho");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initComponents();

		pack();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		barra = new JMenuBar();
		menu = new JMenu("Menu");
		menuItemPaginaInicial = new JMenuItem("Página Inicial");
		menuItemPesquisaTweets = new JMenuItem("Pesquisar Tweets");
		menuItemPesquisaUsuarios = new JMenuItem("Pesquisar usuários");
		menuItemSeguidores = new JMenuItem("Meus seguidores");
		menuItemAjuda = new JMenuItem("Ajuda");
		menuItemSair = new JMenuItem("Sair");
		
		menuItemPaginaInicial.setEnabled(false);
		menuItemPesquisaTweets.setEnabled(false);
		menuItemPesquisaUsuarios.setEnabled(false);
		menuItemSeguidores.setEnabled(false);

		menuItemPaginaInicial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostre(Paineis.TWEETS);
			}
		});
		
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
		
		menuItemSeguidores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostre(Paineis.MEUS_SEGUIDORES);
			}
		});
		
		menuItemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(ABORT);
			}
		});
		
		menu.add(menuItemPaginaInicial);
		menu.addSeparator();
		menu.add(menuItemPesquisaTweets);
		menu.add(menuItemPesquisaUsuarios);
		menu.addSeparator();
		menu.add(menuItemSeguidores);
		menu.addSeparator();
		menu.add(menuItemAjuda);
		menu.addSeparator();
		menu.add(menuItemSair);
		barra.add(menu);
		setJMenuBar(barra);
		
		painelLogin = new PainelLogin(this, servicosAutent);
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
			this.setContentPane(new PainelLogin(this, servicosAutent));
			break;
		case PESQUISA_TWEETS:
			this.setContentPane(new PainelPesquisaTweets(servicosTwiter));
			break;
		case PESQUISA_USUARIOS:
			this.setContentPane(new PainelPesquisaUsuarios(servicosTwiter));
			break;
		case MEUS_SEGUIDORES:
			this.setContentPane(new PainelMeusSeguidores(servicosTwiter));
			break;
		}
		pack();
		setLocationRelativeTo(null);
	}

	public void habiliteMenus() {
		menuItemPaginaInicial.setEnabled(true);
		menuItemPesquisaTweets.setEnabled(true);
		menuItemPesquisaUsuarios.setEnabled(true);
		menuItemSeguidores.setEnabled(true);
	}
}
