package br.edu.g5.clienttwitter.logic;

import java.util.Calendar;
import java.util.Date;



public class Tweet {
	
	private String mensagem;
	private Usuario autor;
	private long Id;
	private String replyTo;
	private Calendar dataDeCriacao;
	private boolean favoritado;
	private boolean retwitByMe;
	
	public Tweet(){
		this.dataDeCriacao = Calendar.getInstance();
	}
	
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
	
	public Calendar getDataDeCriacao() {
		return dataDeCriacao;
	}

	public void setDataDeCriacao(Date criadoEm) {
		this.dataDeCriacao.setTime(criadoEm);
	}

	public boolean isFavoritado() {
		return favoritado;
	}

	public void setFavoritado(boolean favoritado) {
		this.favoritado = favoritado;
	}

	public boolean isRetwitByMe() {
		return retwitByMe;
	}

	public void setRetwitByMe(boolean retwitByMe) {
		this.retwitByMe = retwitByMe;
	}
}
