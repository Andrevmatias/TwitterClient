package br.edu.g5.clienttwitter.logic;

import javax.swing.Icon;

public class Usuario {
	
	private String nome;
	private Icon foto;
	private String nick;
	private long id;
	private boolean isSeguindo;
	private String descricao;
	
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isSeguindo() {
		return isSeguindo;
	}

	public void setSeguindo(boolean isSeguindo) {
		this.isSeguindo = isSeguindo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao(){
		return descricao;
	}

}
