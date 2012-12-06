package br.edu.g5.clienttwitter.ui.models;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import br.edu.g5.clienttwitter.logic.ServicosTwitter;
import br.edu.g5.clienttwitter.logic.Usuario;

public class UsuarioCellRenderer extends JPanel implements
		ListCellRenderer<Usuario> {

	private static final Color COR_SELECIONADO = new Color(200, 200, 255);
	private static final Color COR_NORMAL = new Color(255, 255, 255);

	private BorderLayout layout = new BorderLayout(5, 5);

	private JLabel foto;
	private JLabel nome;
	
	public UsuarioCellRenderer(ServicosTwitter servicosTwitter) {
		this.setLayout(layout);
		initComponents();
	}

	private void initComponents() {
		foto = new JLabel();
		this.add(foto, BorderLayout.WEST);

		nome = new JLabel();
		this.add(nome, BorderLayout.CENTER);

	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends Usuario> list, final Usuario value, int index,
			boolean isSelected, boolean cellHasFocus) {
		this.setBackground(isSelected ? COR_SELECIONADO : COR_NORMAL);
		foto.setIcon(value.getFoto());
		nome.setText(value.getNome());

		return this;
	}

}
