package br.edu.g5.clienttwitter.ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.logic.Usuario;
import br.edu.g5.clienttwitter.ui.models.TweetCellRenderer;

public class PainelListaTweetsUsuario extends JPanel {
	
	private Usuario usuario;
	private ServicosTwitter servicosTwitter;
	private JScrollPane paneTweets;
	private JList<Tweet> listaTweets;
	
	private int paginaAtual = 1;
	
	public PainelListaTweetsUsuario(ServicosTwitter servicosTwitter, Usuario usuario){
		this.usuario = usuario;
		this.servicosTwitter = servicosTwitter;
		
		initComponents();
		posicionaComponents();
	}
	
	private void initComponents() {
		listaTweets = new JList<Tweet>();
		listaTweets.setCellRenderer(new TweetCellRenderer());
		listaTweets.setModel(new DefaultListModel<Tweet>());
		
		paneTweets = new JScrollPane(listaTweets);
		paneTweets.getVerticalScrollBar()
		.addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				if(e.getValueIsAdjusting()) return;

				int viewPosition = paneTweets.getViewport().getViewPosition().y;
				int viewSize = paneTweets.getViewport().getSize().height;
				int listaSize = listaTweets.getSize().height;

				boolean scrolledToEnd = (viewPosition + viewSize) >= listaSize;

				if(scrolledToEnd)
					carreguePagina(++paginaAtual);
			}
		});
	}

	public void posicionaComponents(){
		this.add(paneTweets);
	}
	
	private void carreguePagina(int numPagina) {
		try{
			List<Tweet> tweets = servicosTwitter.getTweets(usuario.getId(), numPagina);
			int index = tweets.size() * (numPagina - 1);
	
			for(Tweet tweet : tweets){
				DefaultListModel<Tweet> model =
						(DefaultListModel<Tweet>)listaTweets.getModel();
	
				if(model.size() > index)
					model.add(index, tweet); //Adiciona na página correta
				else
					model.addElement(tweet); //Caso a página ainda não tenha sido carregada
	
				index++;
			}
		}catch(TwitterException e){
			JOptionPane.showMessageDialog(this, "Erro ao carregar tweets",
					"Carregar Tweets", JOptionPane.ERROR_MESSAGE);
		}
	}
}
