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
	private ServicosTwitter twitterInterface;

	private PainelLogin painelLogin;

	public JanelaPrincipal(ServicosTwitter twitterInterface) {
		this.twitterInterface = twitterInterface;

		setTitle("The Passarinho");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		initComponents();

		pack();
		setLocationRelativeTo(null);
	}

	private void initComponents() {
		barra = new JMenuBar();
		menu = new JMenu("Menu");
		menuItemAjuda = new JMenuItem("Ajuda");
		menuItemSair = new JMenuItem("Sair");

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
		menu.add(menuItemAjuda);
		menu.add(menuItemSair);
		barra.add(menu);
		setJMenuBar(barra);
		
		painelLogin = new PainelLogin(this, twitterInterface);
		this.setContentPane(painelLogin);
	}

	public void interaja() {
		this.setVisible(true);
	}

	public void mostre(Paineis tweets) {
		switch (tweets) {
		case TWEETS:
			this.setContentPane(new PainelTweets(twitterInterface));
			break;
		case LOGIN:
			this.setContentPane(new PainelLogin(this, twitterInterface));
			break;
		}
		pack();
		setLocationRelativeTo(null);
	}
}