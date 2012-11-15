package br.edu.g5.clienttwitter.logic;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import br.edu.g5.clienttwitter.logic.exceptions.ImpossivelAbrirBrowserException;

public class ServicosTwitter {

	private static final int TWEETS_POR_PAGINA = 20;
	private Twitter twitterManager;
	private RequestToken requestToken;

	public ServicosTwitter() {
		this.twitterManager = TwitterFactory.getSingleton();
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
		tweet.setAutor(this.convertUsuario(status.getUser()));
		tweet.setMensagem(status.getText());
		tweet.setId(status.getId());
		tweet.setReplyTo(geraReplyTo(status.getUserMentionEntities()));
		return tweet;
	}

	private String geraReplyTo(UserMentionEntity[] userMentionEntities) {
		String replyTo ="";
		for (int i = 0; i < userMentionEntities.length; i++) {
			replyTo+= "@" + userMentionEntities[i].getScreenName() +" ";
		}
		return replyTo;		
	}

	private Usuario convertUsuario(User source) {
		Usuario autor = new Usuario();
		autor.setFoto(new ImageIcon(source.getProfileImageURL()));
		autor.setNome(source.getName());
		autor.setNick(source.getScreenName());
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

	public Usuario[] pesquisarUsuarios(String argumento) throws TwitterException {
		ResponseList<User> usuarios = 
				twitterManager.searchUsers(argumento, 1);
		
		List<Usuario> usuariosModel = new ArrayList<Usuario>();
		for(User usuario : usuarios)
			usuariosModel.add(convertUsuario(usuario));
		
		return usuariosModel.toArray(new Usuario[0]);
	}


	public Tweet[] pesquisarTweets(String Argumento) throws TwitterException{
		Query query = new Query(Argumento);
	    QueryResult resultado;
	    List<Tweet> tweets1 = new ArrayList<Tweet>();	    
	    resultado = twitterManager.search(query);
		 
		    for(int i=0; i < resultado.getTweets().size(); i++){
		    	Tweet tweet1 = new Tweet();
		    	tweet1.setMensagem(resultado.getTweets().get(i).getText());
		    	tweet1.setId(resultado.getTweets().get(i).getId());
		    	tweet1.setReplyTo(this.geraReplyTo(resultado.getTweets().get(i).getUserMentionEntities()));
		    	Usuario autor = new Usuario();
		    	autor.setFoto(new ImageIcon(resultado.getTweets().get(i).getProfileImageUrl())); //rever
		    	autor.setNick(""); //rever
		    	autor.setNick(""); //rever
		    	tweet1.setAutor(autor);
		    	tweets1.add(tweet1);
		    }
	
	    return tweets1.toArray(new Tweet[0]);

	}
	
}