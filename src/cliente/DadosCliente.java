package cliente;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DadosCliente extends JPanel implements MouseListener{

	static public int dado1, dado2, turno;						//Valor do dado 1, valor do dado 2 e valor do jogador que est� jogando agora(1, 2, 3 ou 4 para azul, amarelo, vermelho e verde)
	static public boolean dado1usado=true, dado2usado=true;	//Indica se determinado dado ja foi utilizado na jogada atual
	Random random;
	JLabel label1, label2;								//Icone clicavel dos dados 1 e 2
	ImageIcon[] DadoImg;								//Vetor que possui as imagens dos dados
	
	public DadosCliente() {
		DadoImg = new ImageIcon[7];
		for(int i=1; i<7; i++) {
			String nomeImg = "Dado" + i + ".png";
			DadoImg[i] = new ImageIcon(nomeImg);
			DadoImg[i] = new ImageIcon(DadoImg[i].getImage().getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH));
		}
		random = new Random();
		turno = 0;
		this.setBackground(Color.white);
		this.setBounds(706, 0, 295, 705);
		this.setLayout(null);
		
		JPanel molduraDados = new JPanel();
		molduraDados.setBounds(70, 280, 145, 80);
		molduraDados.setLayout(new GridLayout(0, 2, 5, 0));
		molduraDados.setOpaque(false);
		
		
		label1 = new JLabel();
		label1.setIcon(DadoImg[6]);
		label1.addMouseListener(this);
		label2 = new JLabel();
		label2.setIcon(DadoImg[6]);
		label2.addMouseListener(this);
		
		molduraDados.add(label1);
		molduraDados.add(label2);
		
		this.add(molduraDados);
		this.setVisible(true);
	}
	
	/*static public void proxTurno() {
		if(DadosCliente.dado1 == 6 && DadosCliente.dado2 == 6) {
			//repete o turno
		}
		else {
			if(++turno >= 4) {
				turno = 0;
			}
		}
		System.out.println("Jogador atual: " + turno);
	}*/

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(ServerConnection.ID == ServerConnection.turnoAtual)
		{
			ClientLudo.rolar_dados();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
