package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import twitter4j.TwitterException;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class PainelInfUsuario extends JPanel {

	private Usuario usuario;
	private JButton btnFollow;
	private JButton btnUnfollow;
	private JLabel textoInf;
	private ServicosTwitter servicosTwitter;

	public PainelInfUsuario(Usuario usuario){
		this.usuario = usuario;
		this.setBackground(Color.white);
		iniciaComponents();
		posicionaComponents();
	}

	private void posicionaComponents() {
		this.setLayout(new BorderLayout());
		this.add(btnFollow, BorderLayout.LINE_START);
		this.add(btnUnfollow, BorderLayout.PAGE_START);
		this.add(textoInf);
		usuario.getFoto();
		usuario.getNick();
		usuario.getNome();
	}

	private void iniciaComponents() {
		btnFollow = new JButton("Follow");
		btnFollow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					servicosTwitter.seguir(usuario.getId());
				} catch (TwitterException arg0) {
					//TODO
				}

			}
		});
		btnUnfollow = new JButton("Unfollow");
		btnUnfollow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					servicosTwitter.unfollow(usuario.getId());
				} catch (TwitterException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao parar de seguir pessoa");
				}
			}
		});
		textoInf = new JLabel(gereInfHTML());
	}

	private String gereInfHTML() {
		StringBuilder builderInf = new StringBuilder();
		builderInf.append(usuario.getNome())
				  .append(usuario.getFoto())
				  .append(usuario.getNick());
		return builderInf.toString();
	}

}
