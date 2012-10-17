package br.edu.ufsc.clienttwitter.logic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterInterface {

	private Twitter twitterManager;
	private RequestToken requestToken;

	public TwitterInterface(Twitter twitterManager) {
		this.twitterManager = twitterManager;
		this.twitterManager.setOAuthConsumer("2dcs3CVXhfMM1lWL2rUSdQ", "Qi0HAa6dVB0O1XNM2u9hjxZih367dZ8mYS0NLw");
	}

	public void login(String codigo) throws TwitterException {
        AccessToken accessToken = 
        		twitterManager.getOAuthAccessToken(requestToken, codigo);
        twitterManager.setOAuthAccessToken(accessToken);
     }

	public List<Tweet> getTweets(int numPagina) {
		List<Tweet> tweetsModel = new ArrayList<Tweet>();
		try {
			Paging paging = new Paging(numPagina, 20);
			ResponseList<Status> tweets;
			tweets = twitterManager.getHomeTimeline(paging);
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
		tweet.setAutor(this.convertAutor(status.getUser()));
		tweet.setMensagem(status.getText());
		return tweet;
	}

	private Autor convertAutor(User source) {
		Autor autor = new Autor();
		autor.setFoto(new ImageIcon(source.getProfileImageURL()));
		autor.setNome(source.getName());
		return autor;
	}
	
	public void twitar(String tweet) throws TwitterException{
		//TODO: Permitir que seja enviado um tweet do usurio
		twitterManager.updateStatus(tweet);
	}

	public void abrirPaginaDeAutorizacao() {
		// TODO: Dar suporte a sistemas que não dão suporte a Desktop
		Desktop desktop = Desktop.getDesktop();
		try{
			requestToken = twitterManager.getOAuthRequestToken();
			desktop.browse(new URI(requestToken.getAuthorizationURL())); 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}

