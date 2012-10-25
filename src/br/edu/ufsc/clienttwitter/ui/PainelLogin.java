package br.edu.ufsc.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import twitter4j.TwitterException;
import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.logic.exceptions.ImpossivelAbrirBrowserException;
import br.edu.ufsc.clienttwitter.ui.enums.Paineis;

public class PainelLogin extends JPanel {

	private static final String DICA_CODIGO = "Digite aqui seu c贸digo";
	private static final String ENDERECO_IMAGEM_PASSARINHO = "http://www.essaseoutras.xpg.com.br/wp-content/uploads/2012/03/twitter-logo.jpg";
	private static final String ENDERECO_IMAGEM_BOTAO_ENTRAR = "http://i.imgur.com/GOfpA.png";
	private TwitterInterface twitterInterface;
	private JTextField textCodigo;
	private JLabel imagem;
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
		try {
			imagem = new JLabel(new ImageIcon(new URL(
					ENDERECO_IMAGEM_PASSARINHO)));
			botaoLogin = new JButton(new ImageIcon(new URL(
					ENDERECO_IMAGEM_BOTAO_ENTRAR)));
		} catch (MalformedURLException e1) {
			// Improv谩vel
		}
		textCodigo = new JTextField(13);
		textCodigo.setText(DICA_CODIGO);
		textCodigo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textCodigo.getText().equals(""))
					textCodigo.setText(DICA_CODIGO);
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (textCodigo.getText().equals(DICA_CODIGO))
					textCodigo.setText("");
			}
		});
		textCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					try {
						twitterInterface.login(textCodigo.getText());
						janelaPrincipal.mostre(Paineis.Tweets);
					} catch (TwitterException ex) {
						JOptionPane.showMessageDialog(null, "C骴igo inv醠ido");
					}
			}
		});

		botaoLogin.setBackground(null);
		botaoLogin.setHideActionText(true);
		botaoLogin.setBorderPainted(false);
		botaoLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});

		lblGerarCodigo = new JLabel("<html><u>Gerar c贸digo para entrar</u>");
		lblGerarCodigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblGerarCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abraPaginaDeAutorizacao();
			}
		});
	}

	private void positionateComponents() {
		JLabel lblCodigo = new JLabel("C贸digo");
		LayoutManager border1 = new BorderLayout();
		this.setLayout(border1);

		JPanel painelImagem = new JPanel();
		JPanel meio = new JPanel();
		JPanel inferiorr = new JPanel();
		painelImagem.add(imagem);
		painelImagem.setBackground(Color.white);
		meio.setBackground(Color.white);
		inferiorr.setBackground(Color.white);
		meio.add(lblCodigo);
		meio.add(textCodigo);
		meio.add(botaoLogin);
		inferiorr.add(lblGerarCodigo);

		this.add(painelImagem, BorderLayout.NORTH);
		this.add(meio, BorderLayout.CENTER);
		this.add(inferiorr, BorderLayout.SOUTH);
	}

	private void login() {
		try {
			twitterInterface.login(textCodigo.getText());
			janelaPrincipal.mostre(Paineis.Tweets);
		} catch (TwitterException ex) {
			JOptionPane.showMessageDialog(this, "C贸digo inv谩lido", "C贸digo",
					JOptionPane.ERROR_MESSAGE);
		} catch (IllegalStateException exState) {
			JOptionPane.showMessageDialog(this,
					"Gere o c贸digo antes de entrar", "Gere c贸digo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void abraPaginaDeAutorizacao() {
		try {
			twitterInterface.abrirPaginaDeAutorizacao();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this,
					"Erro ao abrir p谩gina de autoriza莽茫o");
		} catch (ImpossivelAbrirBrowserException e) {
			JOptionPane.showMessageDialog(this, "Favor acessar " + e.getUri()
					+ "para gerar o c贸digo de autentica莽茫o",
					"Gerar c贸digo", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
