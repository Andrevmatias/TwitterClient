package br.edu.g5.clienttwitter.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	protected PainelPesquisa() {
		initComponents();
	}
	
	private void initComponents() {
		this.add(new JLabel("Nome"));
		
		textArgumento = new JTextField();
		this.add(textArgumento);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pesquisar(textArgumento.getText());
			}
		});
		this.add(btnPesquisar);
		
		listPesquisa = new JList<T>();
		listPesquisa.setCellRenderer(getCellRenderer());
		this.add(listPesquisa);
	}
	
	protected JList<T> getJList(){
		return this.listPesquisa;
	}
	
	protected abstract void pesquisar(String argumento);
	
	protected abstract ListCellRenderer<T> getCellRenderer();
}
