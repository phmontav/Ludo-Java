package game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TelaConexao extends Thread{
	
	JFrame frame = new JFrame();
	JButton botao = new JButton("Conectar");
	JPanel panel = new JPanel();
	//ImageIcon fundo = new ImageIcon("TelaInicialLudo.png");
	JLabel voce = new JLabel("TesteSupremo kkkkk");
	JLabel[] j = new JLabel[4];
	JLabel[] j_conect = new JLabel[4];
	JLabel[] j_pronto = new JLabel[4];


	TelaConexao() {	
		frame.setTitle("LUDO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000, 745);
		frame.setLocationRelativeTo(null);
		frame.setBackground(Color.green);
		
		//panel.setBackground(Color.black);
		panel.setOpaque(true);
		panel.setBounds(350, 0, 300, 745);
		panel.add(botao);
		
		for(int i=0; i<4; i++) {
			j[i] = new JLabel();
			j_conect[i] = new JLabel();
			j_pronto[i] = new JLabel();
			j[i].setBounds(0, 41*i, 250, 40);
			j[i].setText("Jogador " + (i+1));
			j_conect[i].setBounds(300, 41*i, 250, 40);
			j_conect[i].setText(" esperando ");
			j_pronto[i].setBounds(350, 41*i, 250, 40);
			j_pronto[i].setText("não pronto");
			panel.add(j[i]);
			panel.add(j_conect[i]);
			panel.add(j_pronto[i]);
		}
		
		frame.setVisible(true);
		frame.add(panel);
		frame.repaint();
	}
}
