package br.edu.ufsc.clienttwitter.ui.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import br.edu.ufsc.clienttwitter.logic.Tweet;
import br.edu.ufsc.clienttwitter.ui.format.TweetFormatter;

public class TweetCellRenderer extends JPanel implements ListCellRenderer<Tweet> {

	private static final Color COR_SELECIONADO = new Color(200,200,255);
	private static final Color COR_NORMAL = new Color(255, 255, 255);

	private BorderLayout layout = new BorderLayout(5, 0);
	
	private TweetFormatter formatter = new TweetFormatter();
	
	private JLabel lblFoto;
	private JEditorPane txtTweet;
	
	public TweetCellRenderer() {
		this.setSize(500, 100);
		
		this.setLayout(layout);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		
		initComponents();
	}
	
	private void initComponents() {
		lblFoto = new JLabel();
		this.add(lblFoto, BorderLayout.WEST);
		
		txtTweet = new JEditorPane("text/html","<html>");
		txtTweet.setEditable(false);
		txtTweet.setFont(new Font("Verdana", Font.BOLD, 12));
		this.add(txtTweet, BorderLayout.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Tweet> list,
			Tweet value, int index, boolean isSelected, boolean cellHasFocus) {
		
		lblFoto.setIcon(value.getAutor().getFoto());
		txtTweet.setText(formatter.formatHTML(value.getMensagem()));
		
		setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		txtTweet.setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		
		return this;
	}
}
