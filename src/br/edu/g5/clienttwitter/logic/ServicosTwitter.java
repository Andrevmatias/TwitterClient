package br.edu.g5.clienttwitter.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import twitter4j.PagableResponseList;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import br.edu.g5.clienttwitter.logic.exceptions.PaginaInexistenteException;
import br.edu.g5.clienttwitter.logic.utils.Conversoes;

public class ServicosTwitter {

	private static final int TWEETS_POR_PAGINA = 20;
	private Twitter twitterManager;

	public ServicosTwitter() {
		this.twitterManager = TwitterFactory.getSingleton();
	}
	
	public List<Tweet> getTweets(int numPagina) throws TwitterException {
		List<Tweet> tweetsModel = new ArrayList<Tweet>();
		
		Paging paging = new Paging(numPagina, TWEETS_POR_PAGINA);
		
		ResponseList<Status> tweets;
		tweets = twitterManager.getHomeTimeline(paging);
		
		for (Status tweet : tweets) {
			Tweet tweetModel = Conversoes.convertTweet(tweet);
			tweetsModel.add(tweetModel);
		}

		return tweetsModel;
	}
	
	public List<Tweet> getTweets(long id, int numPagina) throws TwitterException {
		List<Tweet> tweetsModel = new ArrayList<Tweet>();
		
		Paging paging = new Paging(numPagina, TWEETS_POR_PAGINA);
		
		ResponseList<Status> tweets;
		tweets = twitterManager.getUserTimeline(id, paging);
		
		for (Status tweet : tweets) {
			Tweet tweetModel = Conversoes.convertTweet(tweet);
			tweetsModel.add(tweetModel);
		}

		return tweetsModel;
	}

	public void twitar(String tweet) throws TwitterException {
		twitterManager.updateStatus(tweet);
	}

	
	public void retwittar(long idTweet) throws TwitterException{
		twitterManager.retweetStatus(idTweet);
	}

	public List<Usuario> pesquisarUsuarios(String argumento, int numPagina) throws TwitterException {
		ResponseList<User> usuarios = 
				twitterManager.searchUsers(argumento, numPagina);
		
		List<Usuario> usuariosModel = new ArrayList<Usuario>();
		for(User usuario : usuarios)
			usuariosModel.add(Conversoes.convertUsuario(usuario));
		
		return usuariosModel;
	}


	public PesquisaDeTweets pesquisarTweets(String Argumento) throws TwitterException{
		Query query = new Query(Argumento);
		query.setCount(TWEETS_POR_PAGINA);
		return new PesquisaDeTweets(twitterManager, query);
	}

	public void follow(long id) throws TwitterException {
		twitterManager.createFriendship(id);
			
	}
	
	public void unfollow(long id) throws TwitterException{
		twitterManager.destroyFriendship(id);
 	}
	
	public void favoritar(long id) throws TwitterException {
		twitterManager.createFavorite(id);
	}
	
	public void enviarDirectMessage(String nick, String msg) throws TwitterException{
		twitterManager.sendDirectMessage(nick, msg);
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
			seguidores.add(Conversoes.convertUsuario(seguidor));
		
		return seguidores;
	}
}
