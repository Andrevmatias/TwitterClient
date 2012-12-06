package br.edu.g5.clienttwitter.ui.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

	public String generateTimeString(Calendar dataDeCriacao) {
		Calendar agora = Calendar.getInstance();
		
		long agoraEmMilissegundos = agora.getTimeInMillis();
		long dataEmMilissegundos = dataDeCriacao.getTimeInMillis();
		long diferenca = agoraEmMilissegundos - dataEmMilissegundos;
		
		double diferencaEmDias = diferenca / 86400000d;
		if(diferencaEmDias > 1d)
			return "em " + new SimpleDateFormat("dd/MM/yyyy")
				.format(dataDeCriacao.getTime());
		
		int diferencaEmHoras = 
				agora.get(Calendar.HOUR_OF_DAY) - dataDeCriacao.get(Calendar.HOUR_OF_DAY);
		if(diferencaEmHoras >= 1)
			return "a " + diferencaEmHoras + " hora(s)";
		
		int diferencaEmMinutos = 
				agora.get(Calendar.MINUTE) - dataDeCriacao.get(Calendar.MINUTE);
		if(diferencaEmMinutos >= 1)
			return "a " + diferencaEmMinutos + " minuto(s)";
		
		return "agora";
	}

	
}
