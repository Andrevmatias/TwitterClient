package br.edu.ufsc.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

import br.edu.ufsc.clienttwitter.logic.Tweet;
import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.models.TweetCellRenderer;

public class PainelTweets extends JPanel {

	private TwitterInterface twitterInterface;
	private JList<Tweet> listaTweets;
	private JScrollPane paneTweets;
	private JTextArea textTweet;
	private JButton twittar;

	public PainelTweets(TwitterInterface twitterInterface) {
		super(new BorderLayout(3, 3));
		this.twitterInterface = twitterInterface;
		
		initComponents();
	}

	private void initComponents() {
		Tweet[] tweets = (Tweet[]) twitterInterface.getTweets(1).toArray(new Tweet[0]);
		listaTweets = new JList<Tweet>(tweets);
		listaTweets.setCellRenderer(new TweetCellRenderer());
		
		paneTweets = new JScrollPane(listaTweets);
		this.add(paneTweets, BorderLayout.CENTER);
		
		textTweet = new JTextArea(5, 10);
		textTweet.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				return ((JTextComponent)input).getText().length() <= 140;
			}
		});
		
		this.add(textTweet, BorderLayout.PAGE_END);
		
		twittar = new JButton();
		twittar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				twitterInterface.twitar(textTweet.getText());
			}
		});
		this.add(twittar, BorderLayout.EAST);
		
	}

}
