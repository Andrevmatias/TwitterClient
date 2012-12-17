package br.edu.g5.clienttwitter.ui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;
import br.edu.g5.clienttwitter.ui.models.UsuarioCellRenderer;

public class PainelPesquisaUsuarios extends PainelPesquisa<Usuario> {

	private ServicosTwitter servicosTwitter;
	private String argumento;

	public PainelPesquisaUsuarios(ServicosTwitter servicosTwiter) {
		super("Nomes");
		this.servicosTwitter = servicosTwiter;
		this.setPreferredSize(new Dimension(300, 600));
		this.getJList().addMouseListener(new MouseAdapter() {
        	@Override
            public void mousePressed(MouseEvent evt) {
                if (SwingUtilities.isLeftMouseButton(evt)) {  
                	new MostradorJanelaInf().mostrarInfUsuarioListUsuarios(getJList());
                }
            }
        });
	}

	@Override
	protected void pesquisar(String argumento) {
		if(argumento == null)
			return;
		
		this.argumento = argumento;
		try {
			List<Usuario> usuarios = 
					servicosTwitter.pesquisarUsuarios(argumento, getPaginaAtual());
			
			DefaultListModel<Usuario> model =
					((DefaultListModel<Usuario>)this.getJList().getModel());
			
			for(Usuario item : usuarios)
				model.addElement(item);
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar usuários", 
					"Pesquisar usuários", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected ListCellRenderer<Usuario> getCellRenderer() {
		return new UsuarioCellRenderer(servicosTwitter);
	}

	@Override
	protected List<Usuario> getPagina(int numPagina) {
		if(this.argumento != null){
			try {
				return servicosTwitter.pesquisarUsuarios(argumento, numPagina);
			}catch(TwitterException e){
				JOptionPane.showMessageDialog(this, "Erro ao carregar página",
						"Carregar Página", JOptionPane.ERROR_MESSAGE);
			}
		}
		return new LinkedList<Usuario>();
	}
}
