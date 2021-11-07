package cliente;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class DadosCliente extends JPanel implements MouseListener, ActionListener{

	static public int dado1, dado2, turno;						//Valor do dado 1, valor do dado 2 e valor do jogador que est� jogando agora(1, 2, 3 ou 4 para azul, amarelo, vermelho e verde)
	static public boolean dado1usado=true, dado2usado=true;	//Indica se determinado dado ja foi utilizado na jogada atual
	JLabel label1, label2;								//Icone clicavel dos dados 1 e 2
	ImageIcon[] DadoImg;								//Vetor que possui as imagens dos dados
	Timer timer;
	int contador = 0;
	
	public DadosCliente() {
		DadoImg = new ImageIcon[7];
		for(int i=1; i<7; i++) {
			String nomeImg = "Dado" + i + ".png";
			DadoImg[i] = new ImageIcon(nomeImg);
			DadoImg[i] = new ImageIcon(DadoImg[i].getImage().getScaledInstance(70, 70,  Image.SCALE_SMOOTH));
		}
		timer = new Timer(100, this);
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

	public void animacao() {
		timer.start();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(ServerConnection.ID == ServerConnection.turnoAtual)
		{
			ClientLudo.rolar_dados();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		label1.setIcon(DadoImg[(contador % 6 + 1)]);
		label2.setIcon(DadoImg[(contador % 6 + 1)]);
		contador++;
		if(contador == 6) {
			contador = 0;
			timer.stop();
			label1.setIcon(DadoImg[dado1]);
			label2.setIcon(DadoImg[dado2]);
		}
		Grid.camadas.repaint();
	}
}
