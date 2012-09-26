package br.edu.ufsc.clienttwitter.logic;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Twitter;

public class TwitterInterface {

	private Twitter twitterManager;

	public TwitterInterface(Twitter twitterManager) {
		this.twitterManager = twitterManager;
	}

	public void login(String login, String senha) {
		//TODO Implementar login
	}

	public List<Tweet> getTweets(int numPagina) {
		// TODO: Retornar a página numPagina de Tweets
		Tweet tweet = new Tweet();
		tweet.setMensagem("Teste");
		
		List<Tweet> tweets = new ArrayList<Tweet>();
		tweets.add(tweet);
		
		return tweets;
	}

}
