package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TelaConexao implements Runnable, ActionListener{
	
	static JFrame frame = new JFrame();
	static JButton botao = new JButton("PRONTO");
	static JPanel panel = new JPanel();
	//ImageIcon fundo = new ImageIcon("TelaInicialLudo.png");
	static JLabel voce = new JLabel("Voc� � o Jogador " + (ClientTest.ID+1));
	static JLabel[] j = new JLabel[4];
	static JLabel[] j_conect = new JLabel[4];
	static JLabel[] j_pronto = new JLabel[4];
	
	ServerConnection serverConn;
	static int[] jStatusBackup = {0, 0, 0, 0};


	TelaConexao(ServerConnection serverConn) {	
		this.serverConn = serverConn;
		frame.setTitle("LUDO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(1000, 745);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(Color.white);
		
		botao.setBounds(120, 550, 254, 39);
		botao.setFocusable(false);
		botao.addActionListener(this);
		botao.setBackground(new Color(0x2dc067));
		botao.setForeground(Color.white);
		botao.setFont(new Font("Arial", Font.BOLD, 15));
		
		panel.setBackground(Color.white);
		panel.setOpaque(true);
		panel.setBounds(230, 0, 540, 745);
		panel.add(botao);
		panel.setLayout(null);
		
		voce.setBounds(110, 40, 300, 100);
		voce.setFont(new Font("Arial", Font.BOLD, 30));
		voce.setForeground(new Color(0x2dc067));
		panel.add(voce);
		
		for(int i = 0; i < 4; i++) {
			j[i] = new JLabel();
			j_conect[i] = new JLabel();
			j_pronto[i] = new JLabel();
			j[i].setBounds(0, 200+70*i, 200, 50);
			j[i].setText("Jogador " + (i+1));
			j[i].setFont(new Font("Arial", Font.BOLD, 30));
			
			j_conect[i].setBounds(170, 200+70*i, 200, 50);
			//j_conect[i].setText(" esperando ");
			j_conect[i].setFont(new Font("Arial", Font.BOLD, 30));
			j_conect[i].setForeground(Color.red);
			
			j_pronto[i].setBounds(370, 200+70*i, 200, 50);
			//j_pronto[i].setText("n�o pronto");
			j_pronto[i].setFont(new Font("Arial", Font.BOLD, 30));
			j_pronto[i].setForeground(Color.red);
			
			panel.add(j[i]);
			panel.add(j_conect[i]);
			panel.add(j_pronto[i]);
		}
		
		j[0].setForeground(new Color(0x00e8fc));
		j[1].setForeground(new Color(0xf9c846));
		j[2].setForeground(new Color(0xf96e46));
		j[3].setForeground(new Color(0x0fff95));
		
		frame.setVisible(true);
		frame.add(panel);
	}

	static public void atualizar(int id, int[] jStatus) {
		voce.setText("Voc� � o Jogador " + (id+1));
		jStatusBackup = jStatus;
	}

	@Override
	public void run() {
		while(true) {
			for(int i = 0; i < 4; i++) {
				switch (jStatusBackup[i]) {
				case 0:
					j_conect[i].setText(" esperando ");
					j_conect[i].setForeground(Color.red);
					j_pronto[i].setText("n�o pronto");
					j_pronto[i].setForeground(Color.red);
					break;
				case 1:
					j_conect[i].setText(" conectado ");
					j_conect[i].setForeground(Color.green);
					j_pronto[i].setText("n�o pronto");
					j_pronto[i].setForeground(Color.red);
					break;
				case 2:
					j_conect[i].setText(" conectado ");
					j_conect[i].setForeground(Color.green);
					j_pronto[i].setText("pronto");
					j_pronto[i].setForeground(Color.green);
					break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		serverConn.enviarMensagem("INICIAR_JOGO " + ClientTest.ID + " 1");
	}
}
