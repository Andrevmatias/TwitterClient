package br.edu.g5.clienttwitter.logic.utils;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.UserMentionEntity;
import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.logic.Usuario;

public final class Conversoes {

	public static Tweet convertTweet(Status status) throws IllegalStateException, TwitterException {
		Tweet tweet = new Tweet();
		tweet.setAutor(convertUsuario(status.getUser()));
		tweet.setMensagem(status.getText());
		tweet.setId(status.getId());
		tweet.setDataDeCriacao(status.getCreatedAt());
		tweet.setReplyTo(geraReplyTo(status.getUserMentionEntities()));
		tweet.setRetwitByMe(status.isRetweetedByMe());
		tweet.setFavoritado(status.isFavorited());
		return tweet;
	}
	
	public static Usuario convertUsuario(User source) throws IllegalStateException, TwitterException {
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
		autor.setDescricao(source.getDescription());
		autor.setSeguindo(source.isProfileBackgroundTiled());
		autor.setCriadoEm(source.getCreatedAt());
		autor.setQuantidadeSeguindo(source.getFollowersCount());
		autor.setQuantidadeAmigos(source.getFriendsCount());
		return autor;
	}
	
	private static String geraReplyTo(UserMentionEntity[] userMentionEntities) {
		StringBuilder replyTo = new StringBuilder();
		for (int i = 0; i < userMentionEntities.length; i++) {
			replyTo.append("@" + userMentionEntities[i].getScreenName() +" ");
		}
		return replyTo.toString();		
	}
}
