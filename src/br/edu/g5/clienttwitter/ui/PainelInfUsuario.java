package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class PainelInfUsuario extends JPanel {

	private Usuario usuario;
	private JButton btnFollow, btnUnfollow, btnDM;
	private JLabel textoInf, foto;
	private JTextField campoDM;
	private JPanel painelBtns, painelInformacoes, painelDM, painelListaTweets;
	private ServicosTwitter servicosTwitter;

	public PainelInfUsuario(Usuario usuario, ServicosTwitter servicosTwitter){
		this.servicosTwitter = servicosTwitter;
		this.usuario = usuario;
		this.setBackground(Color.white);
		iniciaComponents();
		posicionaComponents();
	}

	private void posicionaComponents() {
		this.setLayout(new BorderLayout());
		this.add(painelInformacoes, BorderLayout.NORTH);
		this.add(painelBtns, BorderLayout.SOUTH);
		this.add(painelDM, BorderLayout.CENTER);
		this.add(painelListaTweets, BorderLayout.WEST);
		if(usuario.isSeguindo()){
			this.painelBtns.add(btnUnfollow);
			this.painelDM.add(campoDM);
		} else {
			this.painelBtns.add(btnFollow);
		}
		this.painelDM.add(campoDM);
		this.painelInformacoes.add(foto);
		this.painelInformacoes.add(textoInf);
	}

	private void iniciaComponents() {
		painelBtns = new JPanel();
		painelDM = new JPanel();
		painelInformacoes = new JPanel();
		painelListaTweets = new JPanel();
		campoDM = new JTextField(10);
		foto = new JLabel(usuario.getFoto());
		btnDM = new JButton("Enviar mensagem");
		btnDM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					servicosTwitter.enviarDirectMessage(usuario.getId(), campoDM.getText());
				} catch (TwitterException e) {
					e.printStackTrace();
				}
			}
		});

		btnUnfollow = new JButton("Unfollow");
		btnUnfollow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				unfollow();
			}
		});
		btnFollow = new JButton("Follow");		
		btnFollow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				follow();
				JOptionPane.showMessageDialog(null, "Seguindo " + "@" + usuario.getNick(), 
						"Aviso!", 1);
			}

		});
		this.add(btnFollow);
		textoInf = new JLabel("<html><b>" + usuario.getNome() + 
				"</b><br/>" + "@" + usuario.getNick() + 
				"<br/><i>" + usuario.getDescricao() + "</i>");
	}
	
	private void follow() {
		try {
			servicosTwitter.follow(this.usuario.getId());
		} catch (TwitterException arg0) {
			JOptionPane.showMessageDialog(null, "Erro ao seguir " + usuario.getNome());
		}
	}

	private void unfollow(){
		try {
			servicosTwitter.unfollow(usuario.getId());
		} catch (TwitterException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao parar de seguir " + usuario.getNome());
		}
	}
}
