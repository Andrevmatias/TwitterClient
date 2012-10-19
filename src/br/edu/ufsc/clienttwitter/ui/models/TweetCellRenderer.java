package br.edu.ufsc.clienttwitter.ui.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import br.edu.ufsc.clienttwitter.logic.Tweet;
import br.edu.ufsc.clienttwitter.ui.format.TweetFormatter;

public class TweetCellRenderer extends JPanel implements ListCellRenderer<Tweet> {

	private static final Color COR_SELECIONADO = new Color(200,200,255);
	private static final Color COR_NORMAL = new Color(255, 255, 255);

	private BorderLayout layout = new BorderLayout(5, 5);
	
	private TweetFormatter formatter = new TweetFormatter();
	
	private JLabel lblFoto;
	private JEditorPane txtTweet;
	private JLabel lblAutor;
	
	public TweetCellRenderer() {
		this.setSize(500, 100);
		
		this.setLayout(layout);
		this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray));
		
		initComponents();
	}
	
	private void initComponents() {
		lblFoto = new JLabel();
		this.add(lblFoto, BorderLayout.WEST);
		
		lblAutor = new JLabel();
		this.add(lblAutor, BorderLayout.NORTH);
		
		txtTweet = new JEditorPane("text/html", "");
		txtTweet.setEditable(false);
		txtTweet.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException | URISyntaxException e1) {
					JOptionPane.showMessageDialog(null, 
							"Erro ao acessar link", "Erro", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
			}
		});
		
		this.add(txtTweet, BorderLayout.CENTER);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Tweet> list,
			Tweet value, int index, boolean isSelected, boolean cellHasFocus) {
		
		lblFoto.setIcon(value.getAutor().getFoto());
		lblAutor.setText(value.getAutor().getNome());
		txtTweet.setText(formatter.formatHTML(value.getMensagem()));
		
		setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		txtTweet.setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		
		return this;
	}
}
