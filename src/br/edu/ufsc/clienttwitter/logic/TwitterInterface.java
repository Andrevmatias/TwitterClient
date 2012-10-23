package br.edu.ufsc.clienttwitter.logic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import br.edu.ufsc.clienttwitter.logic.exceptions.ImpossivelAbrirBrowserException;

import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterInterface {

	private static final int TWEETS_POR_PAGINA = 20;
	private Twitter twitterManager;
	private RequestToken requestToken;

	public TwitterInterface(Twitter twitterManager) {
		this.twitterManager = twitterManager;
		this.twitterManager.setOAuthConsumer("2dcs3CVXhfMM1lWL2rUSdQ",
				"Qi0HAa6dVB0O1XNM2u9hjxZih367dZ8mYS0NLw");
	}

	public void login(String codigo) throws TwitterException {
		AccessToken accessToken = twitterManager.getOAuthAccessToken(
				requestToken, codigo);
		twitterManager.setOAuthAccessToken(accessToken);
	}

	public Tweet[] getTweets(int numPagina) throws TwitterException {
		List<Tweet> tweetsModel = new ArrayList<Tweet>();
		
		Paging paging = new Paging(numPagina, TWEETS_POR_PAGINA);
		
		ResponseList<Status> tweets;
		tweets = twitterManager.getHomeTimeline(paging);
		
		for (Status tweet : tweets) {
			Tweet tweetModel = this.convertTweet(tweet);
			tweetsModel.add(tweetModel);
		}

		return tweetsModel.toArray(new Tweet[0]);
	}

	private Tweet convertTweet(Status status) {
		Tweet tweet = new Tweet();
		tweet.setAutor(this.convertAutor(status.getUser()));
		tweet.setMensagem(status.getText());
		tweet.setId(status.getId());
		return tweet;
	}

	private Autor convertAutor(User source) {
		Autor autor = new Autor();
		autor.setFoto(new ImageIcon(source.getProfileImageURL()));
		autor.setNome(source.getName());
		return autor;
	}

	public void twitar(String tweet) throws TwitterException {
		twitterManager.updateStatus(tweet);
	}

	public void abrirPaginaDeAutorizacao() throws TwitterException, ImpossivelAbrirBrowserException {
		Desktop desktop = Desktop.getDesktop();
		requestToken = twitterManager.getOAuthRequestToken();
		String url = requestToken.getAuthorizationURL();
		try {
			desktop.browse(new URI(url));
		} catch (IOException | UnsupportedOperationException e) {
			throw new ImpossivelAbrirBrowserException(url, e);
		} catch (URISyntaxException e) {
			//Improvável
			e.printStackTrace();
		}
	}
	
	public void retwittar(long idTweet) throws TwitterException{
		twitterManager.retweetStatus(idTweet);
	}

}
