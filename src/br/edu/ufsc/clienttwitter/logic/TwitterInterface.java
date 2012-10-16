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

	public void login(String codigo) {
        //TODO Implementar login
        AccessToken accessToken = null;
        try {
            while( accessToken == null){
                 if(codigo.length() > 0){
                       accessToken = twitterManager.getOAuthAccessToken(requestToken, codigo);
                 } else{
                       accessToken = twitterManager.getOAuthAccessToken();
                   }
            }
        } catch (TwitterException e) {
        	e.printStackTrace();
        }
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
	
	public void twitar(String tweet){
		//TODO: Permitir que seja enviado um tweet do usurio
		try {
			Status status = twitterManager.updateStatus(tweet);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void abrirPaginaDeAutorizacao() {
		// TODO: Abre a página para gerar código de autorização
		Desktop desktop = Desktop.getDesktop();
		try{
			requestToken = twitterManager.getOAuthRequestToken();
			desktop.browse(new URI(requestToken.getAuthorizationURL())); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

