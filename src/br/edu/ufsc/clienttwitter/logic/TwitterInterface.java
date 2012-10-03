package br.edu.ufsc.clienttwitter.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

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
		try {
			tweet.setFoto(new ImageIcon(new URL("http://www.foundhistory.org/wp-content/themes/cutline_mod/images/Twitter_32x32.png")));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		List<Tweet> tweets = new ArrayList<Tweet>();
		for(int i = 0; i < 40; i++)
			tweets.add(tweet);
		
		return tweets;
	}

}
