package br.edu.ufsc.clienttwitter.ui.format;

public class TweetFormatter {

	public String formatHTML(String tweet) {
		StringBuilder builder = new StringBuilder("<html>");
		//Separado por espaços
		String[] palavras = tweet.split("\\s");
		
		for(String palavra : palavras){
			if(palavra.length() > 0){
				if(palavra.charAt(0) == '@'){
					builder.append("<font color=\"pink\">" + palavra + "</font> ");
				}
				else{
					builder.append(palavra + " ");
				}
			}
		}
		builder.append("</html>");
		return builder.toString();
	}

	
}
