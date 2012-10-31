package br.edu.ufsc.clienttwitter.logic;

import javax.swing.Icon;

public class Autor {
	
	private String nome;
	private Icon foto;
	private String nick;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Icon getFoto() {
		return foto;
	}
	
	public void setFoto(Icon foto) {
		this.foto = foto;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

}
