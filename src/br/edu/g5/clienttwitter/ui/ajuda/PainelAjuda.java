package br.edu.g5.clienttwitter.ui.ajuda;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JScrollPane;


public class PainelAjuda extends JScrollPane{
	
	private JLabel textoAjuda;

	public PainelAjuda() {
		this.setName("Ajuda");
		this.setSize(300, 200);
		this.getViewport().setBackground(Color.white);
		iniciaComponents();
		posicionaComponents();

	}

	private void posicionaComponents() {
		this.getViewport().add(textoAjuda);
	}

	private void iniciaComponents() {
		textoAjuda = new JLabel(gereAjudaHTML());
	}

	private String gereAjudaHTML() {
		StringBuilder builderAjuda = new StringBuilder();
		builderAjuda
			.append("<html>")
			.append("<div style=\"width: 300px; margin: 10px;\">")
			
			.append("<h1>Ajuda</h1>")
			
			.append("<p>")
			.append("<h2>Como entrar</h2>")
			.append("Para acessar sua conta pelo The Passarinho " +
					"é necessário gerar um código. Após gerado, " +
					"insira no campo e clique em entrar.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como gerar o código</h2>")
			.append("Para gerar o código basta clicar no link " +
					"'Gerar código para acesso' e seguir os " +
					"passos no site.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como Twittar</h2>")
			.append("Para twittar basta inserir o texto no campo" +
					" inferior e apertar ENTER. Vale lembrar que " +
					"o limite de caracteres é 140.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como Retwittar</h2>")
			.append("Para retwittar basta clicar com o botão direito " +
					"do mouse no tweet desejado e selecionar a " +
					"opção 'Retwittar'.")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Lista de Tweets</h2>")
			.append("Caso deseje ver tweets antigos basta rolar o scroll " +
					"do painel de tweets até o fim e seus tweets antigos " +
					"irão ser carregados.")
			.append("</p>")
			
			.append("<br/>")
			.append("<hr/>")
			
			.append("<p>")
			.append("<h2>Como Responder</h2>")
			.append("Para retwittar basta clicar com o botão direito " +
					"do mouse no tweet desejado e selecionar a " +
					"opção \"Reply\"." +
					"Após isso você será redirecionado para o campo de tweetar onde deve ser escrita a resposta.")
			.append("</p>")

			.append("<p>")
			.append("<h2>Como pesquisar tweets</h2>")
			.append("Para pesquisar por tweets, use o menu superior" +
					"e clique em \"Pesquisar Tweets\", digite a palavra" +
					"ou frase que você deseja pesquisar na caixa de pesquisa" +
					"e clique em \"Pesquisar\".")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como pesquisar usuários</h2>")
			.append("Para pesquisar por usuários do Twitter, use o menu" +
					"superior e clique em \"Pesquisar usuários\", digite " +
					"o nome, parte dele, na caixa de pesquisa e clique em " +
					"\"Pesquisar\".")
			.append("</p>")
						
			.append("<p>")
			.append("<h2>Como seguir usuários</h2>")
			.append("Após abrir painel de informações do usuário desejado " +
					"clique em \"Follow\".")
			.append("</p>")
			
			.append("<br/>")
			.append("<hr/>")

			.append("<p>")
			.append("<h2>Como visualizar painel de infomações de usuários</h2>")
			.append("O painel está disponível em três localidades:<br/>" +
					"1) Painel pesquisar de usuários: Após pesquisar pelo usuário clique sobre ele, o painel será exibido em uma nova janela.<br/>" +
					"2) Painel meus seguidores: Após pesquisar pelo usuário clique sobre ele, o painel será exibido em uma nova janela.<br/>" +
					"3) Página inicial: Clica que botão direito no tweet do usuário desejado e na opção informações do usuário.<br/>")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como deixar de seguir usuários</h2>")
			.append("Após abrir painel de informações do usuário desejado " +
					"clique em \"Unfollow\".")
			.append("</p>")
			
			.append("<p>")
			.append("<h2>Como favoritar Tweets</h2>")
			.append("Para favoritarr basta clicar com o botão direito " +
					"do mouse no tweet desejado e selecionar a " +
					"opção \"Favoritar\" .")
			.append("</p>")	
			
			.append("<p>")
			.append("<h2>Como enviar mensagem direta</h2>")
			.append("Após abrir painel de informações do usuário desejado " +
					"preencha o campo disponível e clique em enviar DM.")
			.append("</p>")

			.append("<p>")
			.append("<h2>Como ver seus seguidores</h2>")
			.append("Para ver seus seguidores vá no menu e na opção \"Meus seguidores\" ")
			.append("</p>")

			
			.append("</div>");
		
		return builderAjuda.toString();
	}

}
