package br.edu.g5.clienttwitter.logic.exceptions;

public class ImpossivelAbrirBrowserException extends Exception {
	private String uri;

	public ImpossivelAbrirBrowserException(String uri, Throwable e){
		super(e);
		this.uri = uri;
	}
	
	public ImpossivelAbrirBrowserException(String uri){
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}
}
