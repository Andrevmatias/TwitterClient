package br.edu.ufsc.clienttwitter.ui;

import javax.swing.JList;
import javax.swing.JPanel;

import br.edu.ufsc.clienttwitter.logic.Tweet;
import br.edu.ufsc.clienttwitter.logic.TwitterInterface;

public class PainelTweets extends JPanel {

	private TwitterInterface twitterInterface;
	private JList<Tweet> listaTweets;

	public PainelTweets(TwitterInterface twitterInterface) {
		this.twitterInterface = twitterInterface;
		
		initComponents();
	}

	private void initComponents() {
		Tweet[] tweets = (Tweet[]) twitterInterface.getTweets(1).toArray(new Tweet[0]);
		listaTweets = new JList<Tweet>(tweets);
		
		this.add(listaTweets);
	}

}
