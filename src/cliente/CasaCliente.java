package cliente;

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

public class CasaCliente extends JPanel implements MouseListener{
	
	public int pos;
	public int x, y;
	public int[] n_pecas = {0, 0, 0, 0};
	public List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	public boolean possivel;
	public boolean nascer;
	public boolean pecaSaindo;
	public JLayeredPane camadasRef;
	Color tint = new Color(0xcbc0d3);
	Color tintPossivel = new Color(0x785964);
	Color tintSelected = new Color(0x785964);
	Color atual = new Color(0xcbc0d3);
	
	public CasaCliente(int n, int y_n, int x_n, JLayeredPane camadas){
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
		
		if(pos == 0) {
			atual = tint = new Color(0x00e8fc);
			tintPossivel = new Color(0x00b5c5);
			tintSelected = new Color(0x00b5c5);
		}
		if(pos == 12) {
			atual = tint = new Color(0xf9c846);
			tintPossivel = new Color(0xc39d38);
			tintSelected = new Color(0xc39d38);
		}
		if(pos == 24) {
			atual = tint = new Color(0xf96e46);
			tintPossivel = new Color(0xc65838);
			tintSelected = new Color(0xc65838);
		}
		if(pos == 36) {
			atual = tint = new Color(0x0fff95);
			tintPossivel = new Color(0x0ec172);
			tintSelected = new Color(0x0ec172);
		}
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
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(ServerConnection.ID == ServerConnection.turnoAtual)
		{
			ClientLudo.clicar_casa(pos);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
