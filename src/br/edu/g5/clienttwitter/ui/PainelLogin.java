package br.edu.g5.clienttwitter.ui;

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
import br.edu.g5.clienttwitter.logic.ServicosAuntent;
import br.edu.g5.clienttwitter.logic.exceptions.ImpossivelAbrirBrowserException;
import br.edu.g5.clienttwitter.ui.enums.Paineis;

public class PainelLogin extends JPanel {

	private static final String DICA_CODIGO = "Digite aqui seu código";
	private static final String ENDERECO_IMAGEM_PASSARINHO = "http://www.essaseoutras.xpg.com.br/wp-content/uploads/2012/03/twitter-logo.jpg";
	private static final String ENDERECO_IMAGEM_BOTAO_ENTRAR = "http://i.imgur.com/GOfpA.png";
	private ServicosAuntent twitterInterface;
	private JTextField textCodigo;
	private JLabel imagem;
	private JButton botaoLogin;
	private JLabel lblGerarCodigo;
	private JanelaPrincipal janelaPrincipal;

	private GroupLayout layout = new GroupLayout(this);

	public PainelLogin(JanelaPrincipal janelaPrincipal,
			ServicosAuntent twitterInterface) {
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
			// Improvável
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
					login();
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

		lblGerarCodigo = new JLabel("<html><u>Gerar código para entrar</u>");
		lblGerarCodigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblGerarCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				abraPaginaDeAutorizacao();
			}
		});
	}

	private void positionateComponents() {
		JLabel lblCodigo = new JLabel("Código");
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
			janelaPrincipal.habiliteMenus();
			janelaPrincipal.mostre(Paineis.TWEETS);
		} catch (TwitterException ex) {
			JOptionPane.showMessageDialog(this, "Código inválido! Gere o código novamente.", "Código",
					JOptionPane.ERROR_MESSAGE);
		} catch (IllegalStateException exState) {
			JOptionPane.showMessageDialog(this,
					"Gere o código antes de entrar", "Gere código",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void abraPaginaDeAutorizacao() {
		try {
			twitterInterface.abrirPaginaDeAutorizacao();
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this,
					"Erro ao abrir página de autorização");
		} catch (ImpossivelAbrirBrowserException e) {
			JOptionPane.showMessageDialog(this, "Favor acessar " + e.getUri()
					+ "para gerar o código de autenticação",
					"Gerar código", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
