package br.edu.ufsc.clienttwitter.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterInterface {

	private Twitter twitterManager;

	public TwitterInterface(Twitter twitterManager) {
		this.twitterManager = twitterManager;
	}

	public void login(String codigo) {
        //TODO Implementar login
        AccessToken accessToken = null;
        RequestToken requestToken;
        try {
            requestToken = twitterManager.getOAuthRequestToken();
            while( accessToken == null){
                 if(codigo.length() > 0){
                       accessToken = twitterManager.getOAuthAccessToken(requestToken, codigo);
                 } else{
                       accessToken = twitterManager.getOAuthAccessToken();
                   }
            }
        } catch (TwitterException e) {
            if(401 == e.getStatusCode()){
                 //"Unable to get the access token.
                }else{
                  e.printStackTrace();
                }
        }
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
	
	public void Tweet(String tweet){
		//TODO: Permitir que seja enviado um tweet do usurio
		try {
			twitterManager.updateStatus(tweet);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void abrirPaginaDeAutorizacao() {
		// TODO: Abre a página para gerar código de autorização
	}
}
