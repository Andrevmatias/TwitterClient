package br.edu.g5.clienttwitter.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import br.edu.g5.clienttwitter.logic.exceptions.PaginaInexistenteException;

import twitter4j.PagableResponseList;
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

public class ServicosTwitter {

	private static final int TWEETS_POR_PAGINA = 20;
	private Twitter twitterManager;

	public ServicosTwitter() {
		this.twitterManager = TwitterFactory.getSingleton();
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
		tweet.setDataDeCriacao(status.getCreatedAt());
		tweet.setReplyTo(geraReplyTo(status.getUserMentionEntities()));
		tweet.setRetwitByMe(status.isRetweetedByMe());
		tweet.setFavoritado(status.isFavorited());
		return tweet;
	}

	private String geraReplyTo(UserMentionEntity[] userMentionEntities) {
		StringBuilder replyTo = new StringBuilder();
		for (int i = 0; i < userMentionEntities.length; i++) {
			replyTo.append("@" + userMentionEntities[i].getScreenName() +" ");
		}
		return replyTo.toString();		
	}

	private Usuario convertUsuario(User source) {
		Usuario autor = new Usuario();
		try {
			autor.setFoto(new ImageIcon(new URL(source.getProfileImageURL())));
		} catch (MalformedURLException e) {
			//Improvável
			e.printStackTrace();
		}
		autor.setNome(source.getName());
		autor.setNick(source.getScreenName());
		autor.setId(source.getId());
		return autor;
	}

	public void twitar(String tweet) throws TwitterException {
		twitterManager.updateStatus(tweet);
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


	public List<Tweet> pesquisarTweets(String Argumento) throws TwitterException{
		Query query = new Query(Argumento);
	    QueryResult resultado;
	    List<Tweet> tweets = new LinkedList<Tweet>();	    
	    resultado = twitterManager.search(query);
		 
	    for(Status tweet : resultado.getTweets()){
	    	Tweet tweetModel = convertTweet(tweet);
	    	tweets.add(tweetModel);
	    }

	    return tweets;
	}

	public void seguir(long id) throws TwitterException {
		twitterManager.createFriendship(id);
			
	}
	
	public void unfollow(long id) throws TwitterException{
		twitterManager.destroyFriendship(id);
 	}
	
	public void favoritar(long id) throws TwitterException {
		twitterManager.createFavorite(id);
	}

	public List<Usuario> getSeguidores(int numPagina) throws TwitterException, PaginaInexistenteException {
		List<Usuario> seguidores = new LinkedList<>();
		PagableResponseList<User> response = 
				twitterManager.getFollowersList(twitterManager.getId(), -1);
		
		if(numPagina > 1){
			long cursor = 0l;
			
			for(int i = 1; i < numPagina; i++)
				if(response.hasNext())
					cursor = response.getNextCursor();
				else
					throw new PaginaInexistenteException("A página de seguidores não existe");
			
			response = twitterManager.getFollowersList(twitterManager.getId(), cursor);
		}
		
		for(User seguidor : response)
			seguidores.add(convertUsuario(seguidor));
		
		return seguidores;
	}
}
