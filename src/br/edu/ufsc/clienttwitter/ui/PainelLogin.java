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

public class PainelLogin extends JPanel {

	private TwitterInterface twitterInterface;
	
	private JTextField textUsuario;
	private JTextField textSenha;
	
	private JButton botaoLogin;

	public PainelLogin(TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		initComponents();
	}

	private void initComponents() {
		textUsuario = new JTextField(15);
		textSenha = new JTextField(15);
		
		this.add(new JLabel("Login"));
		this.add(textUsuario);
		this.add(new JLabel("Senha"));
		this.add(textSenha);
		
		botaoLogin = new JButton("Login");
		botaoLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					twitterInterface
						.login(textUsuario.getText(), textSenha.getText());
					((JComponent)e.getSource()).getParent().setVisible(false);
				}
				catch (RuntimeException ex){
					//TODO: Tratar erro de login
				}
			}
		});
		
		this.add(botaoLogin);
	}

}
