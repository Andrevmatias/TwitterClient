package br.edu.g5.clienttwitter.ui;

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
		this.setSize(100, 300);
	}

	@Override
	protected void pesquisar(String argumento) {
		Tweet[] tweets;
		try {
			tweets = servicosTwitter.pesquisarTweets(argumento);
			this.getJList().setListData(tweets);
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
