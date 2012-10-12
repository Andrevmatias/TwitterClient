package br.edu.ufsc.clienttwitter.ui.models;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import br.edu.ufsc.clienttwitter.logic.Tweet;

public class TweetCellRenderer extends JLabel implements ListCellRenderer<Tweet> {

	private static final Color COR_SELECIONADO = new Color(200,200,255);
	private static final Color COR_NORMAL = new Color(255, 255, 255);

	@Override
	public Component getListCellRendererComponent(JList<? extends Tweet> list,
			Tweet value, int index, boolean isSelected, boolean cellHasFocus) {
		
		setIcon(value.getAutor().getFoto());
		setText(value.getMensagem());
		
		setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		
		return this;
	}
	
}
