package br.edu.g5.clienttwitter.ui;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import twitter4j.TwitterException;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;
import br.edu.g5.clienttwitter.ui.models.UsuarioCellRenderer;

public class PainelPesquisaUsuarios extends PainelPesquisa<Usuario> {

	private ServicosTwitter servicosTwitter;
	private JanelaInfUsuario janelaInfUsuario;

	public PainelPesquisaUsuarios(ServicosTwitter servicosTwiter) {
		super("Nomes");
		this.servicosTwitter = servicosTwiter;
		this.setPreferredSize(new Dimension(300, 600));
		this.getJList().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				mostrarInfUsuario();
			}
		});
	}

	private void mostrarInfUsuario() {
		Usuario selectedValue = getJList().getSelectedValue();
		janelaInfUsuario = new JanelaInfUsuario(selectedValue, servicosTwitter);
		janelaInfUsuario.mostre();
	}

	@Override
	protected void pesquisar(String argumento) {
		try {
			Usuario[] usuarios = 
					servicosTwitter.pesquisarUsuarios(argumento);
			this.getJList().setListData(usuarios);
		} catch (TwitterException e) {
			JOptionPane.showMessageDialog(this, "Erro ao pesquisar usuários", 
					"Pesquisar usuários", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	protected ListCellRenderer<Usuario> getCellRenderer() {
		return new UsuarioCellRenderer(servicosTwitter);
	}
}
