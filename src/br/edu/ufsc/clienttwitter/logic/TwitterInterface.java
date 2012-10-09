package br.edu.ufsc.clienttwitter.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterInterface {

	private Twitter twitterManager;

	public TwitterInterface(Twitter twitterManager) {
		this.twitterManager = twitterManager;
	}

	public void login(String codigoDeAcesso) {
		//TODO Implementar login
	}

	public List<Tweet> getTweets(int numPagina) {
		// TODO: Retornar a página numPagina de Tweets
		List<Tweet> tweetsModel = new ArrayList<Tweet>();
		try {
			Paging paging = new Paging(numPagina, 20);
			ResponseList<Status> tweets;
			tweets = twitterManager.getUserTimeline(paging);
			for(Status tweet : tweets){
				Tweet tweetModel = this.convertTweet(tweet);
				tweetsModel.add(tweetModel);
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		return tweetsModel;
	}
	
	private Tweet convertTweet(Status status) {
		Tweet tweet = new Tweet();
		tweet.setAutor(this.convertAutor(status.getSource()));
		tweet.setMensagem(status.getText());
		
		return tweet;
	}

	private Autor convertAutor(String source) {
		Autor autor = new Autor();
		autor.setNome(source);
		return autor;
	}

	public void Tweet(String tweet){
		//TODO: Permitir que seja enviado um tweet do usuário
	}

	public void abrirPaginaDeAutorizacao() {
		// TODO: Abre a página para gerar código de autorização
	}
}
