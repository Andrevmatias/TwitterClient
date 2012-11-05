package br.edu.g5.clienttwitter.ui.ajuda;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;


public class PainelPrincipalAjuda extends JPanel{
	
	private JButton sobre;
	private JButton ajuda;
	private PainelAjuda painelAjuda;
	private PainelSobre painelSobre;
	private JPanel painelCard = new JPanel();
	private JPanel painelLateral;
	
	private CardLayout cardLayout = new CardLayout();

	public PainelPrincipalAjuda(){
		this.setBackground(Color.white);
		painelAjuda = new PainelAjuda();
		painelSobre = new PainelSobre();
		posicionaComponets();
	}

	private void posicionaComponets() {
        this.setLayout(new BorderLayout());
        
        ajuda = new JButton("Ajuda");
        ajuda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(painelCard, "painelAjuda");
			}
		});
        
        sobre = new JButton("Sobre");
        sobre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(painelCard, "painelSobre");
			}
		});
        
        painelLateral = new JPanel();
        painelLateral.setLayout(new BoxLayout(painelLateral, BoxLayout.Y_AXIS));
        painelLateral.add(ajuda);
        painelLateral.add(sobre);
        this.add(painelLateral, BorderLayout.LINE_START);
              
        painelCard.setLayout(cardLayout);
        painelCard.add(painelAjuda, "painelAjuda");
        painelCard.add(painelSobre, "painelSobre");
        this.add(painelCard, BorderLayout.CENTER);
	}
}
