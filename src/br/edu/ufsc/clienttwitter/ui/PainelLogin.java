package br.edu.ufsc.clienttwitter.ui;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
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
	}

	private void initComponents() {
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
				catch (RuntimeException ex){
					JOptionPane.showMessageDialog(null, "Código inválido");
				}
			}
		});
		this.add(botaoLogin);
		
		lblGerarCodigo = new JLabel("<html><u>Gerar código para acesso</u>");
		lblGerarCodigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblGerarCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				twitterInterface.abrirPaginaDeAutorizacao();
			}
		});
		this.add(lblGerarCodigo);
		
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(new JLabel("Código"))
				.addComponent(textCodigo)
				.addComponent(botaoLogin)
		);
	}

}
