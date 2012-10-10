package br.edu.ufsc.clienttwitter.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.enums.Paineis;

public class PainelLogin extends JPanel {

	private TwitterInterface twitterInterface;
	private JTextField textCodigo;
	
	private JButton botaoLogin;
	private JButton btnGerarCodigo;
	private JanelaPrincipal janelaPrincipal;

	public PainelLogin(JanelaPrincipal janelaPrincipal, 
			TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		this.janelaPrincipal = janelaPrincipal;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		initComponents();
	}

	private void initComponents() {
		textCodigo = new JTextField(15);
		
		btnGerarCodigo = new JButton("Permitir acesso à conta");
		btnGerarCodigo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				twitterInterface.abrirPaginaDeAutorizacao();
			}
		});
		this.add(btnGerarCodigo);
		
		
		this.add(new JLabel("Código"));
		this.add(textCodigo);
		
		botaoLogin = new JButton("Login");
		botaoLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					twitterInterface
						.login(textCodigo.getText());
					janelaPrincipal.setContentPane(new PainelTweets(twitterInterface));
				}
				catch (RuntimeException ex){
					ex.printStackTrace();
					//TODO: Tratar erro de login
				}
			}
		});
		
		this.add(botaoLogin);
	}

}
