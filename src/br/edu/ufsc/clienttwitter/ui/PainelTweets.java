package br.edu.ufsc.clienttwitter.ui;

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
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.text.JTextComponent;

import br.edu.ufsc.clienttwitter.logic.Tweet;
import br.edu.ufsc.clienttwitter.logic.TwitterInterface;
import br.edu.ufsc.clienttwitter.ui.models.TweetCellRenderer;

public class PainelTweets extends JPanel {

	private TwitterInterface twitterInterface;
	private JList<Tweet> listaTweets;
	private JScrollPane paneTweets;
	private JTextArea textTweet;

	private int paginaAtual = 1;
	private Thread atualizadorTweets;

	public PainelTweets(TwitterInterface twitterInterface) {
		super(new BorderLayout(3, 3));
		this.twitterInterface = twitterInterface;

		initComponents();
	}


	private void initComponents() {
		listaTweets = new JList<Tweet>(new DefaultListModel<Tweet>());
		listaTweets.setCellRenderer(new TweetCellRenderer());
		
		 JPopupMenu menu = new JPopupMenu(); 
	        JMenuItem item = new JMenuItem("Retwittar");
	       
	        item.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                Tweet tweet = (Tweet) evt.getSource();
	                System.out.println(tweet.getMensagem());
	            }
	        }
	        );
	       item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	        menu.add(item);       
	       
	        listaTweets.setComponentPopupMenu(menu);
	       
	        listaTweets.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent evt) {
	                if (SwingUtilities.isRightMouseButton(evt)) {
	                    listaTweets.getComponentPopupMenu().show(null, evt.getX(), evt.getY()); 
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
					carregarPagina(++paginaAtual);
			}
		});

		textTweet = new JTextArea(5, 10);
		textTweet.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				return ((JTextComponent)input).getText().length() <= 140;
			}
		});
		textTweet.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					String tweet = textTweet.getText();
					textTweet.setText("");
					new TweetSender(tweet).execute();
				}
			}	
		});
		this.add(textTweet, BorderLayout.PAGE_END);
	}

	private void carregarPagina(int numPagina) {
		Tweet[] tweets = twitterInterface.getTweets(numPagina);
		int index = tweets.length * (numPagina - 1);

		for(Tweet tweet : tweets){
			DefaultListModel<Tweet> model =
					(DefaultListModel<Tweet>)listaTweets.getModel();

			if(model.getSize() < index)
				model.add(index, tweet); //Adiciona na página correta
			else
				model.addElement(tweet); //Caso a página ainda não tenha sido carregada

			index++;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		atualizadorTweets.interrupt();
		atualizadorTweets = null;
		super.finalize();
	}

	private class TweetSender extends SwingWorker<Void, Void>{
		private String tweet;

		public TweetSender(String tweet) {
			this.tweet = tweet;
		}

		@Override
		protected void done() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				//Nada a fazer
			}
			carregarPagina(1);
		}

		@Override
		protected Void doInBackground() throws Exception {
			twitterInterface.twitar(tweet);
			return null;
		}
	}
}