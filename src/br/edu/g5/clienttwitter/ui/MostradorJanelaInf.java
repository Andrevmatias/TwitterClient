package br.edu.g5.clienttwitter.ui;

import javax.swing.JList;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Tweet;
import br.edu.g5.clienttwitter.logic.Usuario;

public class MostradorJanelaInf {
	
	private JanelaInfUsuario janelaInfUsuario;
	private ServicosTwitter servicosTwitter;
	
	public MostradorJanelaInf(){
		this.servicosTwitter = new ServicosTwitter();
	}
		
	
	public void mostrarInfUsuarioListTweets(JList<Tweet> jList) {
		Usuario selectedValue = jList.getSelectedValue().getAutor();
		janelaInfUsuario = new JanelaInfUsuario(selectedValue, servicosTwitter);
		janelaInfUsuario.mostre();	
	}
	
	
	public void mostrarInfUsuarioListUsuarios(JList<Usuario> jList) {
		Usuario selectedValue = jList.getSelectedValue();
		janelaInfUsuario = new JanelaInfUsuario(selectedValue, servicosTwitter);
		janelaInfUsuario.mostre();	
	}
	
}
