package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Origem extends JPanel implements MouseListener{

	int dentro;
	int cor;
	boolean possivel;
	Color tint, selectedTint;

	Origem(int n){
		//this.setPreferredSize(new Dimension(47, 47));
		//this.setPreferredSize(new Dimension(200, 200));
		
		cor = n;
		possivel = false;
		switch(cor) {
			case 1: tint = new Color(0x00e8fc);
				selectedTint = new Color(0x00b5c5);
				this.setBounds(518, 518, 151, 151);
					break;
			case 2: tint = new Color(0xf9c846);
				selectedTint = new Color(0xc39d38);
				this.setBounds(518, 48, 151, 151);
					break;
			case 3: tint = new Color(0xf96e46);
				selectedTint = new Color(0xc65838);
				this.setBounds(48, 48, 151, 151);
					break;
			case 4: tint = new Color(0x0fff95);
				selectedTint = new Color(0x0ec172);
				this.setBounds(48, 518, 151, 151);
					break;
		}
		this.setBackground(Color.white);
		addMouseListener(this);
		this.dentro= 3;
			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.draw
		
		g2d.setColor(tint);
		g2d.fillOval(0, 0, 151, 151);
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
			ChangeColor(tint);
			possivel = false;
			GridTest.movimentoIniciado = false;
		}
		else {
			if(!GridTest.movimentoIniciado) {
				System.out.println(cor);
				ChangeColor(selectedTint);

				GridTest.movimentoIniciado = true;
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
