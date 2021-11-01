package graficos;

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
	
	public int pos;			//ID da posi��o desta casa-------------------------------Cli
	public int x, y;																//---Serv?
	public int[] n_pecas = {0, 0, 0, 0};		//Array com numero de pecas de cada cor--Serv
	public List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();	//-----------Serv
	public boolean possivel;	//Indica se � possivel mover uma pe�a para esta casa-----Serv
	public boolean nascer;	//Indica se � possivel mover uma pe�a para esta casa---------Serv
	public boolean pecaSaindo;	//Indica se uma pe�a est� sendo retirada desta cada------Serv
	public JLayeredPane camadasRef;
	Color tint = new Color(0xcbc0d3);
	Color tintPossivel = new Color(0x785964);
	Color tintSelected = new Color(0x785964);
	Color atual = new Color(0xcbc0d3);		//-----------------------------------Cli
	
	public Casa(int n, int y_n, int x_n, JLayeredPane camadas){
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
	
	public void kill() {
		if(pos != 0 && pos != 12 && pos != 24 && pos != 36) {
			for(int i=0; i<4; i++) {
				if(n_pecas[i] > 0 && i != Dados.turno) {
					while(n_pecas[i] > 0) {
						
						Pecas.coordPeca[i][pecas.get(i).peek()][0] = Pecas.backup[i][pecas.get(i).peek()][0];
						Pecas.coordPeca[i][pecas.get(i).peek()][1] = Pecas.backup[i][pecas.get(i).peek()][1];
						Pecas.posPeca[i][pecas.get(i).peek()] = -1;
						n_pecas[i]--;
						GridTest.origens[i].dentro++;
						GridTest.origens[i].pecas.push(pecas.get(i).peek());
						Pecas.p_juntas[i][pecas.get(i).pop()] = 1;
						camadasRef.repaint();
					}
				}
			}
		}
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
		GridTest.verificar_colisao();
		camadasRef.repaint();
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
