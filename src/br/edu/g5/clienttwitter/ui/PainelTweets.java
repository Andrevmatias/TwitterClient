package br.edu.g5.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.logic.exceptions.JaFavoritadoExcepiton;
import br.edu.g5.clienttwitter.logic.exceptions.JaRetwittadoException;
import br.edu.g5.clienttwitter.ui.models.TweetCellRenderer;

public class PainelTweets extends JPanel {

	private ServicosTwitter twitterInterface;
	
	private JList<Tweet> listaTweets;
	
	private JPopupMenu popupListaTweets;
	private JMenuItem itemFavoritar;
	private JMenuItem itemRetwittar;
	private JMenuItem itemReply;
	private JMenuItem itemInfUsuario;
	
	private JScrollPane paneTweets;
	private JTextArea textTweet;

	private int paginaAtual = 1;


	public PainelTweets(ServicosTwitter twitterInterface) {
		super(new BorderLayout(3, 3));
		this.twitterInterface = twitterInterface;

		this.setSize(100, 300);
		
		initComponents();
		carreguePagina(paginaAtual);
	}

	private void initComponents() {
				
		listaTweets = new JList<Tweet>(new DefaultListModel<Tweet>());
		listaTweets.setCellRenderer(new TweetCellRenderer());
		
		popupListaTweets = new JPopupMenu(); 
        itemRetwittar = new JMenuItem("Retwittar");

        itemFavoritar = new JMenuItem("Favoritar");
        itemFavoritar.addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					favoritaSelecionado();
				} catch (TwitterException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao favoritar!", "Erro", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
        popupListaTweets.add(itemFavoritar);
        
        popupListaTweets.addSeparator();
        
        itemRetwittar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				retwitteSelecionado();
			}
		});
        popupListaTweets.add(itemRetwittar);       

        itemReply = new JMenuItem("Reply");
        itemReply.addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				replySelecionado();
			}
		});
        popupListaTweets.add(itemReply);
        
        itemInfUsuario = new JMenuItem("InformaÁıes do usu·rio");
        itemInfUsuario.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new MostradorJanelaInf().mostrarInfUsuarioListTweets(listaTweets);
				}
		});
        
        popupListaTweets.addSeparator();
        popupListaTweets.add(itemInfUsuario);
        
        listaTweets.addMouseListener(new MouseAdapter() {
        	@Override
            public void mousePressed(MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {           	
                	listaTweets.setSelectedIndex(
                			listaTweets.locationToIndex(evt.getPoint()));
                    popupListaTweets.show(listaTweets, evt.getX(), evt.getY()); 
                }
            }
        });

		paneTweets = new JScrollPane(listaTweets);
		this.add(paneTweets, BorderLayout.CENTER);
		
		
		
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
		
		textTweet = new JTextArea(5, 10);
		textTweet.setLineWrap(true);
		textTweet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					envieTweet();
			}	
		});
		this.add(textTweet, BorderLayout.PAGE_END);
	}
	
	private void envieTweet(){
		String tweet = textTweet.getText();
		if(tweet.length() <= 140){
			textTweet.setText("");
			new TweetSender(tweet).execute();
		}
		else{
			JOptionPane.showMessageDialog(this, 
					"O tweet pode ter, no m√°ximo, 140 caracteres", 
					"140 caracteres", JOptionPane.ERROR_MESSAGE);
		}
	}
	


	private void replySelecionado() {
	  	Tweet tweet = listaTweets.getSelectedValue();
	  	textTweet.setText(tweet.getReplyTo());
	  	textTweet.requestFocus();
	}
	
	private void favoritaSelecionado() throws TwitterException {
		Tweet tweet = listaTweets.getSelectedValue();
			try {
				if(tweet.isFavoritado() == true)
					throw new JaFavoritadoExcepiton();
				twitterInterface.favoritar(tweet.getId());
				tweet.setFavoritado(true);
				JOptionPane.showMessageDialog(this, 
						"Favoritado com sucesso!");
			} catch (JaFavoritadoExcepiton e) {
				JOptionPane.showMessageDialog(this, 
						"Tweet j· favoritado.");
			}
		
	}
	
	private void retwitteSelecionado(){
		Tweet tweet = listaTweets.getSelectedValue();
		try {
			if(tweet.isRetwitByMe() == true)
				throw new JaRetwittadoException();
			twitterInterface.retwittar(tweet.getId());
			JOptionPane.showMessageDialog(null, "Retwittado com sucesso.");
		} catch (TwitterException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao retwittar");
			ex.printStackTrace();
		} catch (JaRetwittadoException e) {
			JOptionPane.showMessageDialog(null, "Tweet j· retwittado.");
		}
	}

	private void carreguePagina(int numPagina) {
		try{
			Tweet[] tweets = twitterInterface.getTweets(numPagina);
			int index = tweets.length * (numPagina - 1);
	
			for(Tweet tweet : tweets){
				DefaultListModel<Tweet> model =
						(DefaultListModel<Tweet>)listaTweets.getModel();
	
				if(model.size() > index)
					model.add(index, tweet); //Adiciona na p√°gina correta
				else
					model.addElement(tweet); //Caso a p√°gina ainda n√£o tenha sido carregada
	
				index++;
			}
		}catch(TwitterException e){
			JOptionPane.showMessageDialog(this, "Erro ao carregar tweets",
					"Carregar Tweets", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class TweetSender extends SwingWorker<Void, Void>{
		private String tweet;

		public TweetSender(String tweet) {
			this.tweet = tweet;
		}

		@Override
		protected void done() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				//Nada a fazer
			}
			carreguePagina(1);
		}

		@Override
		protected Void doInBackground() throws Exception {
			twitterInterface.twitar(tweet);
			return null;
		}
	}
	

}