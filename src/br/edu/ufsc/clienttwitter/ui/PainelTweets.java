package br.edu.ufsc.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import javax.swing.text.JTextComponent;

import br.edu.ufsc.clienttwitter.logic.Tweet;
import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.models.TweetCellRenderer;

public class PainelTweets extends JPanel {

	private TwitterInterface twitterInterface;
	private JList<Tweet> listaTweets;
	private JScrollPane paneTweets;
	private JTextArea textTweet;

	public PainelTweets(TwitterInterface twitterInterface) {
		super(new BorderLayout(3, 3));
		this.twitterInterface = twitterInterface;
		
		initComponents();
		carregarPagina(1);
	}

	private void initComponents() {
		listaTweets = new JList<Tweet>(new Tweet[0]);
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
		textTweet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String tweet = textTweet.getText();
					textTweet.setText("");
					new TweetSender(tweet).run();
					repaint();
				}
			}	
		});
		this.add(textTweet, BorderLayout.PAGE_END);
	}

	private void carregarPagina(int numPagina) {
		Tweet[] tweets = (Tweet[]) twitterInterface
				.getTweets(numPagina)
				.toArray(new Tweet[0]);
		listaTweets.setListData(tweets);
	}
	
	private class TweetSender extends SwingWorker<Void, Void>{
		private String tweet;

		public TweetSender(String tweet) {
			this.tweet = tweet;
		}
		
		@Override
		protected void done() {
			carregarPagina(1);
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			twitterInterface.twitar(tweet);
			return null;
		}
	}

}