package br.edu.g5.clienttwitter.ui.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.ui.format.TweetFormatter;

public class TweetCellRenderer extends JPanel implements ListCellRenderer<Tweet> {

	private static final Color COR_SELECIONADO = new Color(200,200,255);
	private static final Color COR_NORMAL = new Color(255, 255, 255);
	private static final Color COR_SELECIONADO_FAVORITADO = new Color(248,252,73);
	private static final Color COR_NORMAL_FAVORITADO = new Color(250, 253, 138);

	private BorderLayout layout = new BorderLayout(5, 5);
	
	private TweetFormatter formatter = new TweetFormatter();
	
	private JLabel lblFoto;
	private JEditorPane txtTweet;
	private JLabel lblAutor;
	private JLabel lblTempo;
	
	public TweetCellRenderer() {
		this.setSize(500, 100);
		
		this.setLayout(layout);
		this.setBorder(BorderFactory
				.createMatteBorder(0, 0, 1, 0, Color.gray));
		
		initComponents();
	}
	
	private void initComponents() {
		lblFoto = new JLabel();
		this.add(lblFoto, BorderLayout.WEST);
		
		lblAutor = new JLabel();
		this.add(lblAutor, BorderLayout.NORTH);
		
		lblTempo = new JLabel();
		lblTempo.setHorizontalAlignment(JLabel.RIGHT);
		this.add(lblTempo, BorderLayout.SOUTH);
		
		txtTweet = new JEditorPane("text/html", "");
		txtTweet.setEditable(false);
		
		this.add(txtTweet, BorderLayout.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Tweet> list,
			Tweet value, int index, boolean isSelected, boolean cellHasFocus) {
		
		lblFoto.setIcon(value.getAutor().getFoto());
		lblAutor.setText(value.getAutor().getNome());
		txtTweet.setText(formatter.formatHTML(value.getMensagem()));
		lblTempo.setText(formatter.generateTimeString(value.getDataDeCriacao()));
		
		if(value.isFavoritado() == true) {
			setBackground(isSelected ? COR_SELECIONADO_FAVORITADO : COR_NORMAL_FAVORITADO);
			txtTweet.setBackground(isSelected ? COR_SELECIONADO_FAVORITADO : COR_NORMAL_FAVORITADO);
		} else {
			setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
			txtTweet.setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
			}
		return this;
	}
}
