package br.edu.ufsc.clienttwitter.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import br.edu.ufsc.clienttwitter.logic.TwitterInterface;


public class PainelPrincipalAjuda extends JPanel implements ActionListener {
	
	private JButton sobre;
	private JButton ajuda;
	private painelAjuda painelAjuda;
	private paineiSobre painelSobre;
	private JPanel painelCard = new JPanel();
	private JPanel painelLateral;
	private JButton sair;
	

	public PainelPrincipalAjuda(){
		this.setBackground(Color.white);
		painelAjuda = new painelAjuda();
		painelSobre = new paineiSobre();
		posicionaComponets();
	}

	private void posicionaComponets() {
        this.setLayout(new BorderLayout());
        
        ajuda = new JButton("Ajuda");
        sobre = new JButton("Sobre");
		sair = new JButton("Sair");

        ajuda.addActionListener(this);
        sobre.addActionListener(this);
        sair.addActionListener(this);
        
        
        painelLateral = new JPanel();
        painelLateral.setLayout( new BorderLayout());
        painelLateral.add(ajuda, BorderLayout.NORTH);
        painelLateral.add(sobre, BorderLayout.WEST);
        //painelLateral.add(sair, BorderLayout.SOUTH);
        this.add(painelLateral, BorderLayout.WEST);
              
        painelCard.setLayout(new CardLayout());
        painelCard.add(painelAjuda, "p1");
        painelCard.add(painelSobre, "p2");
        this.add(painelCard, BorderLayout.CENTER);
           	
		
	}

	@Override
	public void actionPerformed(ActionEvent a) {
      CardLayout cl = (CardLayout) painelCard.getLayout();
      if (a.getSource() == ajuda){
    	  cl.show(painelCard, "p1");
      }
      if  (a.getSource() == sobre){
    	  cl.show(painelCard, "p2");
      }

      if  (a.getSource() == sair){
    	  //implementar sair da janela
    	      	  
      }
    	     	   	  
	}

}
