package br.edu.ufsc.clienttwitter.ui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import twitter4j.TwitterException;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.logic.exceptions.ImpossivelAbrirBrowserException;
import br.edu.ufsc.clienttwitter.ui.enums.Paineis;

public class PainelLogin extends JPanel {

	private TwitterInterface twitterInterface;
	private JTextField textCodigo;
	
	private JButton botaoLogin;
	private JLabel lblGerarCodigo;
	private JanelaPrincipal janelaPrincipal;
	
	private GroupLayout layout = new GroupLayout(this);

	public PainelLogin(JanelaPrincipal janelaPrincipal, 
			TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		this.janelaPrincipal = janelaPrincipal;
		
		this.setLayout(layout);
		
		initComponents();
		positionateComponents();
	}

	private void initComponents() {
		this.add(new JLabel("Cdigo"));
		
		textCodigo = new JTextField(15);
		this.add(textCodigo);
		
		botaoLogin = new JButton("Login");
		botaoLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					twitterInterface.login(textCodigo.getText());
					janelaPrincipal.mostre(Paineis.Tweets);
				}
				catch (TwitterException ex){
					JOptionPane.showMessageDialog(null, "Código inválido");
					ex.printStackTrace();
				}
			}
		});
		this.add(botaoLogin);
		
		lblGerarCodigo = new JLabel("<html><u>Gerar código para acesso</u>");
		lblGerarCodigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblGerarCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				abrirPaginaDeAutorizacao();
			}
		});
		this.add(lblGerarCodigo);
	}

	private void abrirPaginaDeAutorizacao() {
		try {
			twitterInterface.abrirPaginaDeAutorizacao();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Erro ao abrir página de autorização");
		} catch (ImpossivelAbrirBrowserException e) {
			JOptionPane.showMessageDialog(this, "Favor acessar " + 
					e.getUri() + "para gerar o código de autenticação", 
					"Gerar código", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void positionateComponents() {
		JLabel lblCodigo = new JLabel("Código");
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(lblCodigo)
				.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(textCodigo)
						.addComponent(lblGerarCodigo)
				).addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(botaoLogin)
				)
		);
		layout.linkSize(SwingConstants.HORIZONTAL, textCodigo, lblGerarCodigo);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			        .addComponent(lblCodigo)
			        .addComponent(textCodigo)
			        .addComponent(botaoLogin))
			    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
			    		.addComponent(lblGerarCodigo))
			);
	}

}
