package br.edu.g5.clienttwitter.logic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import br.edu.g5.clienttwitter.logic.exceptions.ImpossivelAbrirBrowserException;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class ServicosAuntent {

	private RequestToken requestToken;
	private Twitter twitterManager;

	public ServicosAuntent() {
		this.twitterManager = TwitterFactory.getSingleton();
		this.twitterManager.setOAuthConsumer("2dcs3CVXhfMM1lWL2rUSdQ",
				"Qi0HAa6dVB0O1XNM2u9hjxZih367dZ8mYS0NLw");
	}
	

	public void login(String codigo) throws TwitterException {
		AccessToken accessToken = twitterManager.getOAuthAccessToken(
				requestToken, codigo);
		twitterManager.setOAuthAccessToken(accessToken);
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

}
