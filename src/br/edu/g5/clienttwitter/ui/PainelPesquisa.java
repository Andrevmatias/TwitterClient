package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public abstract class PainelPesquisa<T> extends JPanel {
	
	private JButton btnPesquisar;
	private JTextField textArgumento;
	private JList<T> listPesquisa;
	private JLabel jlbtipoDePesquisa;
	private JanelaPrincipal janelaPrincipal;

	
	protected PainelPesquisa(String tipoDePesquisa) {
		initComponents(tipoDePesquisa);
		posicionaComponentes();
	}
	
	private void posicionaComponentes() {
		this.setLayout(new BorderLayout());
		JPanel painelPesquisa = new JPanel();
		painelPesquisa.add(jlbtipoDePesquisa);
		painelPesquisa.add(textArgumento);
		painelPesquisa.add(btnPesquisar);
		this.add(painelPesquisa, BorderLayout.NORTH);
		this.add(listPesquisa, BorderLayout.CENTER);
	}

	private void initComponents(String tipoDePesquisa) {
		jlbtipoDePesquisa = new JLabel(tipoDePesquisa);
		this.setBackground(Color.WHITE);
		
		textArgumento = new JTextField(10);
		textArgumento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					pesquisar(textArgumento.getText());
					janelaPrincipal.pack();
			}
		});

		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pesquisar(textArgumento.getText());
				janelaPrincipal.pack();
			}
		});
		
		listPesquisa = new JList<T>();
		listPesquisa.setCellRenderer(getCellRenderer());
	}
	
	protected JList<T> getJList(){
		return this.listPesquisa;
	}
	
	protected abstract void pesquisar(String argumento);
	
	protected abstract ListCellRenderer<T> getCellRenderer();
}
