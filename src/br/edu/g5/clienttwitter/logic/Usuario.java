package br.edu.g5.clienttwitter.logic;

import java.util.Date;

import javax.swing.Icon;

public class Usuario {
	
	private String nome;
	private Icon foto;
	private String nick;
	private long id;
	private boolean isSeguindo;
	private String descricao;
	private Date criadoEm;
	private int quantidadeSeguindo;
	private int quantidadeAmigos;
	
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
		if(descricao == null)
			this.descricao = "";
	}
	
	public String getDescricao(){
		return descricao;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public int getQuantidadeSeguindo() {
		return quantidadeSeguindo;
	}

	public void setQuantidadeSeguindo(int quantidadeSeguindo) {
		this.quantidadeSeguindo = quantidadeSeguindo;
	}

	public int getQuantidadeAmigos() {
		return quantidadeAmigos;
	}

	public void setQuantidadeAmigos(int quantidadeAmigos) {
		this.quantidadeAmigos = quantidadeAmigos;
	}

}
