package br.edu.g5.clienttwitter;

import br.edu.g5.clienttwitter.logic.ServicosAuntent;
import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.ui.JanelaPrincipal;

public class Main {

	public static void main(String[] args) {
		ServicosTwitter tServicos = new ServicosTwitter();
		ServicosAuntent tAutent = new ServicosAuntent();
		
		JanelaPrincipal janela = new JanelaPrincipal(tServicos, tAutent);
		
		janela.interaja();
	}

}
