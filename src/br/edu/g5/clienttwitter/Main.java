package br.edu.g5.clienttwitter;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.ui.JanelaPrincipal;

public class Main {

	public static void main(String[] args) {
		ServicosTwitter tServicos = new ServicosTwitter();
		
		JanelaPrincipal janela = new JanelaPrincipal(tServicos);
		
		janela.interaja();
	}

}
