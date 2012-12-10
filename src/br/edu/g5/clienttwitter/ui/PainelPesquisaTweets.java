package br.edu.g5.clienttwitter.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.ui.models.TweetCellRenderer;

public class PainelPesquisaTweets extends PainelPesquisa<Tweet> {

	private ServicosTwitter servicosTwitter;

	public PainelPesquisaTweets(ServicosTwitter servicosTwiter) {
		super("Tweet");
		this.servicosTwitter = servicosTwiter;
		this.setPreferredSize(new Dimension(300, 600));
	}

	@Override
	protected void pesquisar(String argumento) {
		List<Tweet> tweets;
		try {
			tweets = servicosTwitter.pesquisarTweets(argumento);
			this.getJList().setListData(tweets.toArray(new Tweet[0]));
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar Tweets", 
					"Pesquisar Tweets", JOptionPane.ERROR_MESSAGE);
		}
						
	}

	@Override
	protected ListCellRenderer<Tweet> getCellRenderer() {
		return new TweetCellRenderer();
	}

}
