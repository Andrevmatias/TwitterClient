package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;

public abstract class PainelPesquisa<T> extends JPanel {
	
	private JButton btnPesquisar;
	private JTextField textArgumento;
	private JList<T> listPesquisa;
	private JLabel jlbtipoDePesquisa;
	private JScrollPane painelResultado;
	private JPanel painelPesquisa;
	
	private int paginaAtual = 1;
	
	protected PainelPesquisa(String tipoDePesquisa) {
		initComponents(tipoDePesquisa);
		posicionaComponentes();
	}
	
	protected int getPaginaAtual() {
		return this.paginaAtual;
	}
	
	private void posicionaComponentes() {
		this.setLayout(new BorderLayout());
		painelPesquisa.add(jlbtipoDePesquisa);
		painelPesquisa.add(textArgumento);
		painelPesquisa.add(btnPesquisar);
		this.add(painelPesquisa, BorderLayout.NORTH);
		this.add(painelResultado, BorderLayout.CENTER);
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
			}
		});

		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paginaAtual = 1;
				pesquisar(textArgumento.getText());
			}
		});
		
		listPesquisa = new JList<T>();
		listPesquisa.setCellRenderer(getCellRenderer());
		listPesquisa.setModel(new DefaultListModel<T>());
		
		painelPesquisa = new JPanel();
		painelResultado = new JScrollPane(listPesquisa);
		
		painelResultado.getVerticalScrollBar()
		.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if(e.getValueIsAdjusting()) return;

				int viewPosition = painelResultado.getViewport().getViewPosition().y;
				int viewSize = painelResultado.getViewport().getSize().height;
				int listaSize = listPesquisa.getSize().height;

				boolean scrolledToEnd = (viewPosition + viewSize) >= listaSize;

				if(scrolledToEnd)
					carreguePagina(++paginaAtual);
			}
		});
	}

	protected JList<T> getJList(){
		return this.listPesquisa;
	}
	
	private void carreguePagina(int numPagina) {
		List<T> itens = getPagina(numPagina);
		int index = itens.size() * (numPagina - 1);
		
		DefaultListModel<T> model =
					((DefaultListModel<T>)listPesquisa.getModel());
		
		for(T item : itens){
			if(model.size() > index)
				model.add(index, item); //Adiciona na página correta
			else
				model.addElement(item); //Caso a página ainda não tenha sido carregada

			index++;
		}
	}
	
	protected abstract List<T> getPagina(int numPagina);
	
	protected abstract void pesquisar(String argumento);
	
	protected abstract ListCellRenderer<T> getCellRenderer();
}
