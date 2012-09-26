package br.edu.ufsc.clienttwitter;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.JanelaPrincipal;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class Main {

	public static void main(String[] args) {
		Twitter twitterManager = TwitterFactory.getSingleton();
		
		TwitterInterface tInterface = new TwitterInterface(twitterManager);
		
		JanelaPrincipal janela = new JanelaPrincipal(tInterface);
		
		janela.interaja();
	}

}
