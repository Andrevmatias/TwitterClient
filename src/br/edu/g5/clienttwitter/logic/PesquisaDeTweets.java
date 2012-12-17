package br.edu.g5.clienttwitter.logic;

import java.util.LinkedList;
import java.util.List;

import br.edu.g5.clienttwitter.logic.utils.Conversoes;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class PesquisaDeTweets {

	private Query query;
	private Twitter twitterManager;
	private QueryResult resultado;

	public PesquisaDeTweets(Twitter twitterManager, Query query) {
		this.twitterManager = twitterManager;
		this.query = query;
	}

	public List<Tweet> getPaginaAtual() throws TwitterException{
		List<Tweet> tweets = new LinkedList<Tweet>();
		
		if(resultado == null){
		    resultado = twitterManager.search(query);
		}
	    
	    for(Status tweet : resultado.getTweets()){
	    	Tweet tweetModel = Conversoes.convertTweet(tweet);
	    	tweets.add(tweetModel);
	    }

	    return tweets;
	}
	
	public List<Tweet> avancePagina() throws TwitterException{
		this.query = resultado.nextQuery();
		resultado = null;
		return this.getPaginaAtual();
	}
}
