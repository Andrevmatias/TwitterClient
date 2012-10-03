package br.edu.ufsc.clienttwitter.logic;

import javax.swing.Icon;

public class Tweet {
	
	private String mensagem;
	private Autor autor;
	private Icon foto;
	
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
	public Icon getFoto() {
		return this.foto;
	}
	public void setFoto(Icon foto) {
		this.foto = foto;
	}
	@Override
	public String toString() {
		return this.mensagem;
	}
}
