package br.edu.ufsc.clienttwitter.logic;


public class Tweet {
	
	private String mensagem;
	private Autor autor;
	private long Id;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public void setId(long Id){
		this.Id = Id;
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
	public long getId(){
		return this.Id;
	}
}
