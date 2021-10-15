package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Centro extends JPanel implements MouseListener{

	boolean possivel;
	int[] n_pecas = {0, 0, 0, 0};
	List<Stack<Integer>> pecas = new ArrayList<Stack<Integer>>();
	static JLayeredPane camadasRef;
	Image img_atual, img_normal, img_selected;

	Centro(){
		this.setBounds(6*47, 6*47, 141, 141);
		this.setOpaque(false);
		possivel = false;
		addMouseListener(this);
		img_atual = img_normal = new ImageIcon("Centro.png").getImage();
		img_selected = new ImageIcon("Centro.png").getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_atual, 0, 0, null);
	}
	
	public void Selected() {
		img_atual = img_selected;
		repaint();
	}
	
	public void Normal() {
		img_atual = img_normal;
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
		if(possivel) {
			
		}
		//camadasRef.repaint();
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