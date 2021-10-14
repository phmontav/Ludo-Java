package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Casa extends JPanel implements MouseListener{
	
	int pos;			//ID da posição desta casa
	int x, y;
	int[] n_pecas = {0, 0, 0, 0};		//Array com numero de pecas de cada cor
	List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	boolean possivel;	//Indica se é possivel mover uma peça para esta casa
	boolean nascer;	//Indica se é possivel mover uma peça para esta casa
	boolean pecaSaindo;	//Indica se uma peça está sendo retirada desta cada
	JLayeredPane camadasRef;
	Color tint = new Color(0xcbc0d3);
	Color tintPossivel = new Color(0x785964);
	Color tintSelected = new Color(0x785964);
	Color atual = new Color(0xcbc0d3);
	
	Casa(int n, int y_n, int x_n, JLayeredPane camadas){
		this.setPreferredSize(new Dimension(47, 47));
		this.setBackground(Color.white);
		addMouseListener(this);
		this.pos= n;
		possivel = false;
		nascer = false;
		pecaSaindo = false;
		x = x_n*47;
		y = y_n*47;
		camadasRef = camadas;
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
		pecas.add(new Stack<Integer>());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.draw
		g2d.setPaint(atual);
		g2d.fillOval(0, 0, 47, 47);
	}
	
	public void ColorNormal() {
		atual = tint;
		repaint();
	}
	public void ColorPossivel() {
		atual = tintPossivel;
		repaint();
	}
	public void ColorSelected() {
		atual = tintSelected;
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
		
		GridTest.clickCasa(pos);
		camadasRef.repaint();
		/*if(possivel == true) {
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
		}*/
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
