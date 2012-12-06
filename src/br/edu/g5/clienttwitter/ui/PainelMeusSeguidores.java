package br.edu.g5.clienttwitter.ui;

import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;
import br.edu.g5.clienttwitter.ui.models.UsuarioCellRenderer;

public class PainelMeusSeguidores extends JPanel {

	private ServicosTwitter servicosTwiter;
	private JScrollPane paneTweets;
	private JList<Usuario> listaUsuarios;
	
	private int paginaAtual = 1;
	
	public PainelMeusSeguidores(ServicosTwitter servicosTwiter) {
		this.servicosTwiter = servicosTwiter;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.setSize(100, 300);
		
		initComponents();
		
		carreguePagina(paginaAtual);
	}

	private void initComponents() {
		listaUsuarios = new JList<Usuario>(new DefaultListModel<Usuario>());
		listaUsuarios.setCellRenderer(new UsuarioCellRenderer(servicosTwiter));

		paneTweets = new JScrollPane(listaUsuarios);
		this.add(paneTweets);
		
		paneTweets.getVerticalScrollBar()
			.addAdjustmentListener(new AdjustmentListener() {
				@Override
				public void adjustmentValueChanged(AdjustmentEvent e) {
					if(e.getValueIsAdjusting()) return;
	
					int viewPosition = paneTweets.getViewport().getViewPosition().y;
					int viewSize = paneTweets.getViewport().getSize().height;
					int listaSize = listaUsuarios.getSize().height;
	
					boolean scrolledToEnd = (viewPosition + viewSize) >= listaSize;
	
					if(scrolledToEnd)
						carreguePagina(++paginaAtual);
				}
			});
	}
	
	private void carreguePagina(int numPagina) {
		try{
			List<Usuario> usuarios = servicosTwiter.getSeguidores(numPagina);
			int index = usuarios.size() * (numPagina - 1);
	
			for(Usuario usuario : usuarios){
				DefaultListModel<Usuario> model =
						(DefaultListModel<Usuario>)listaUsuarios.getModel();
	
				if(model.size() > index)
					model.add(index, usuario); //Adiciona na página correta
				else
					model.addElement(usuario); //Caso a página ainda não tenha sido carregada
	
				index++;
			}
		}catch(TwitterException e){
			JOptionPane.showMessageDialog(this, "Erro ao carregar tweets",
					"Carregar Tweets", JOptionPane.ERROR_MESSAGE);
		}
	}

}
