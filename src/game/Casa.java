package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Casa extends JPanel implements MouseListener{
	
	int pos;			//ID da posi��o desta casa
	int azul;			//N�mero de pe�as azuis nessa casa
	int amarelo;		//N�mero de pe�as amarelas nessa casa
	int vermelho;		//N�mero de pe�as vermelhas nessa casa
	int verde;			//N�mero de pe�as verdes nessa casa
	boolean possivel;	//Indica se � possivel mover uma pe�a para esta casa
	boolean pecaSaindo;	//Indica se uma pe�a est� sendo retirada desta cada
	Color tint = new Color(0xcbc0d3);
	
	Casa(int n){
		this.setPreferredSize(new Dimension(47, 47));
		this.setBackground(Color.white);
		addMouseListener(this);
		this.pos= n;
		azul = amarelo = vermelho = verde = 0;
		possivel = false;
		pecaSaindo = false;
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.draw
		g2d.setPaint(tint);
		g2d.fillOval(0, 0, 47, 47);
	}
	
	public void ChangeColor(Color nova) {
		tint = nova;
		repaint();
	}
	
	

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
		if(possivel == true) {
			if(pos-Dados.dado1>=0 && !Dados.dado1usado && GridTest.caminho[pos-Dados.dado1].pecaSaindo) {
				ChangeColor(new Color(0xcbc0d3));
				possivel = false;
				GridTest.caminho[pos-Dados.dado1].ChangeColor(new Color(0xcbc0d3));
				GridTest.movimentoIniciado = false;
				GridTest.caminho[pos-Dados.dado1].pecaSaindo = false;
				
				GridTest.caminho[pos-Dados.dado1+Dados.dado2].possivel = GridTest.caminho[pos+Dados.dado2].possivel = false;
				GridTest.caminho[pos-Dados.dado1+Dados.dado2].ChangeColor(new Color(0xcbc0d3));
				GridTest.caminho[pos+Dados.dado2].ChangeColor(new Color(0xcbc0d3));
				Dados.dado1usado = true;
			}
			else {
				if(pos-Dados.dado2>=0 && !Dados.dado2usado && GridTest.caminho[pos-Dados.dado2].pecaSaindo) {
					ChangeColor(new Color(0xcbc0d3));
					possivel = false;
					GridTest.caminho[pos-Dados.dado2].ChangeColor(new Color(0xcbc0d3));
					GridTest.movimentoIniciado = false;
					GridTest.caminho[pos-Dados.dado2].pecaSaindo = false;
					
					GridTest.caminho[pos-Dados.dado2+Dados.dado1].possivel = GridTest.caminho[pos+Dados.dado1].possivel = false;
					GridTest.caminho[pos-Dados.dado2+Dados.dado1].ChangeColor(new Color(0xcbc0d3));
					GridTest.caminho[pos+Dados.dado1].ChangeColor(new Color(0xcbc0d3));
					Dados.dado2usado = true;
				}
				else {
					ChangeColor(new Color(0xcbc0d3));
					possivel = false;
					GridTest.caminho[pos-Dados.dado1-Dados.dado2].ChangeColor(new Color(0xcbc0d3));
					GridTest.movimentoIniciado = false;
					GridTest.caminho[pos-Dados.dado1-Dados.dado2].pecaSaindo = false;
					
					GridTest.caminho[pos-Dados.dado1].possivel = GridTest.caminho[pos-Dados.dado2].possivel = false;
					GridTest.caminho[pos-Dados.dado1].ChangeColor(new Color(0xcbc0d3));
					GridTest.caminho[pos-Dados.dado2].ChangeColor(new Color(0xcbc0d3));
					Dados.dado1usado = Dados.dado2usado = true;
				}
			}
		}
		else {
			if(!GridTest.movimentoIniciado) {
				if(!Dados.dado1usado) {
					ChangeColor(new Color(0x785964));
					GridTest.caminho[pos+Dados.dado1].ChangeColor(new Color(0x785964));
					GridTest.caminho[pos+Dados.dado1].possivel = true;
					GridTest.movimentoIniciado = true;
					pecaSaindo = true;
				}
				if(!Dados.dado2usado) {
					ChangeColor(new Color(0x785964));
					GridTest.caminho[pos+Dados.dado2].ChangeColor(new Color(0x785964));
					GridTest.caminho[pos+Dados.dado2].possivel = true;
					GridTest.movimentoIniciado = true;
					pecaSaindo = true;
				}
				if(!Dados.dado1usado && !Dados.dado2usado) {
					ChangeColor(new Color(0x785964));
					GridTest.caminho[pos+Dados.dado1+Dados.dado2].ChangeColor(new Color(0x785964));
					GridTest.caminho[pos+Dados.dado1+Dados.dado2].possivel = true;
					GridTest.movimentoIniciado = true;
					pecaSaindo = true;
				}
				
			}
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
