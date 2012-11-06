package br.edu.g5.clienttwitter.logic;



public class Tweet {
	
	private String mensagem;
	private Usuario autor;
	private long Id;
	private String replyTo;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public void setId(long Id){
		this.Id = Id;
	}
	public Usuario getAutor() {
		return autor;
	}
	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	@Override
	public String toString() {
		return this.mensagem;
	}
	public long getId(){
		return this.Id;
	}
	public String getReplyTo() {
		return "@" + this.getAutor().getNick() +" " + replyTo;
	}
	
	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
		}
}
