package br.edu.g5.clienttwitter.ui;

import java.awt.Dimension;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.PesquisaDeTweets;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.ui.models.TweetCellRenderer;

public class PainelPesquisaTweets extends PainelPesquisa<Tweet> {

	private ServicosTwitter servicosTwitter;
	private PesquisaDeTweets pesquisa;

	public PainelPesquisaTweets(ServicosTwitter servicosTwiter) {
		super("Tweet");
		this.servicosTwitter = servicosTwiter;
		this.setPreferredSize(new Dimension(400, 600));
	}

	@Override
	protected void pesquisar(String argumento) {
		if(argumento == null)
			return;
		
		List<Tweet> tweets;
		try {
			pesquisa = servicosTwitter.pesquisarTweets(argumento);
			tweets = pesquisa.getPaginaAtual();
			
			DefaultListModel<Tweet> model =
					((DefaultListModel<Tweet>)this.getJList().getModel());
			
			for(Tweet item : tweets)
				model.addElement(item);
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar Tweets", 
					"Pesquisar Tweets", JOptionPane.ERROR_MESSAGE);
		}
						
	}

	@Override
	protected ListCellRenderer<Tweet> getCellRenderer() {
		return new TweetCellRenderer();
	}

	@Override
	protected List<Tweet> getPagina(int numPagina) {
		if(pesquisa != null){
			try {
				return pesquisa.avancePagina();
			}catch(TwitterException e){
				JOptionPane.showMessageDialog(this, "Erro ao carregar página",
						"Carregar Página", JOptionPane.ERROR_MESSAGE);
			}
		}
		return new LinkedList<Tweet>();
	}

}
