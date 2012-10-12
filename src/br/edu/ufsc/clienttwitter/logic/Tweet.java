package br.edu.ufsc.clienttwitter.logic;


public class Tweet {
	
	private String mensagem;
	private Autor autor;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	@Override
	public String toString() {
		return this.mensagem;
	}
}
