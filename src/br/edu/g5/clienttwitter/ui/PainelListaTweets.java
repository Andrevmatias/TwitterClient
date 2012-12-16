package br.edu.g5.clienttwitter.ui;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class PainelListaTweets {
	
	private Usuario usuario;
	private ServicosTwitter servicosTwitter;
	
	public PainelListaTweets(Usuario usuario){
		posicionaComponents();
	}
	
	public void posicionaComponents(){
		try {
			servicosTwitter.getTweets(1, usuario.getId());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}
}
