package br.edu.g5.clienttwitter.ui.models;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import br.edu.g5.clienttwitter.logic.Usuario;

public class UsuarioCellRenderer extends JLabel 
	implements ListCellRenderer<Usuario> {

	private static final Color COR_SELECIONADO = new Color(200,200,255);
	private static final Color COR_NORMAL = new Color(255, 255, 255);
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Usuario> list, Usuario value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		this.setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		this.setText(value.getNome());
		this.setIcon(value.getFoto());
		
		return this;
	}

}
