package br.edu.ufsc.clienttwitter.ui.format;

public class TweetFormatter {

	public String formatHTML(String tweet) {
		StringBuilder builder = new StringBuilder("<html>");
		String style = "width: 250px; " +
					   "font-family: Verdana; " +
				       "font-size: 12pt; ";
		
		builder.append("<body style=\"" + style + "\">");
		
		//Separado por espaços
		String[] palavras = tweet.split("\\s");
		
		for(String palavra : palavras){
			if(palavra.length() > 0){
				if(palavra.charAt(0) == '@'){
					builder.append("<font color=\"#3399FF\">" + palavra + "</font> ");
				}
				else if(palavra.charAt(0) == '#'){
					builder.append("<font color=\"#003366\">" + palavra + "</font> ");
				}
				else if(palavra.contains("http")){
					builder.append("<a href=\""+ palavra +"\">" + palavra + "</a> ");
				}
				else{
					builder.append(palavra + " ");
				}
			}
		}
		
		builder.append("</body>");
		builder.append("</html>");
		return builder.toString();
	}

	
}
