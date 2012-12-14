package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		this.add(painelListaTweets, BorderLayout.EAST);
		this.painelBtns.add(btnFollow);
		this.painelBtns.add(btnUnfollow);
		this.painelDM.add(campoDM);
		this.painelDM.add(btnDM);
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
		btnDM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					enviarDM();
			}
		});
		
		btnDM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				enviarDM();
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
			}

		});
		this.add(btnFollow);
		textoInf = new JLabel("<html><b>" + usuario.getNome()+" (Seguidores:"+" "+ usuario.getQuantidadeSeguindo()
				+")"+ 
				"</b><br/>" + "@" + usuario.getNick() + 
				"<br/><i>" + usuario.getDescricao() + "</i>");
	}
	
	private void follow() {
		try {
			servicosTwitter.follow(this.usuario.getId());
			usuario.setSeguindo(true);
			JOptionPane.showMessageDialog(null, "Seguindo " + "@" + usuario.getNick(), 
					"Aviso!", 1);
		} catch (TwitterException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao seguir " + usuario.getNome() +
					"!" + " Verifique se voc� j� est� seguindo o usu�rio.", "Problema seguir"
					, JOptionPane.WARNING_MESSAGE);
			}
	}

	private void unfollow(){
		try {
			servicosTwitter.unfollow(this.usuario.getId());
			usuario.setSeguindo(false);
			JOptionPane.showMessageDialog(null, "Voc� parou de seguir " + "@" + usuario.getNick(), 
					"Aviso!", 1);
		} catch (TwitterException e1) {
			JOptionPane.showMessageDialog(null, "Erro ao parar de seguir " + usuario.getNome() +
					"!" + " Verifique se voc� est� seguindo o usu�rio.", "Problema ao parar de seguir"
					, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private void enviarDM() {
		try {
			servicosTwitter.enviarDirectMessage(usuario.getNick(), campoDM.getText());
			JOptionPane.showMessageDialog(null,"Mensagem enviada com sucesso!", "Mensagem Direta" , JOptionPane.INFORMATION_MESSAGE);
			campoDM.setText("");
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(null,"Erro ao enviar mensager!" + " Verifique se voc� est� seguindo o usu�rio.",
					"Problema ao enviar mensagem", JOptionPane.ERROR_MESSAGE);
		}
	}
}