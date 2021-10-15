package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Pecas extends JPanel {
	
	static int[][][] backup =   {{{12*47+7, 11*47}, {11*47+7, 12*47+17}, {13*47+7, 12*47+17}},
			   						{{12*47, 2*47}, {13*47, 2*47}, {14*47, 2*47}},
			   						{{2*47, 2*47}, {3*47, 2*47}, {4*47, 2*47}},
			   						{{2*47, 11*47}, {3*47, 11*47}, {4*47, 11*47}}};
	static int[][][] coordPeca = {{{12*47+7, 11*47}, {11*47+7, 12*47+17}, {13*47+7, 12*47+17}},
								{{12*47, 2*47}, {13*47, 2*47}, {14*47, 2*47}},
								{{2*47, 2*47}, {3*47, 2*47}, {4*47, 2*47}},
								{{2*47, 11*47}, {3*47, 11*47}, {4*47, 11*47}}};
	int x_test, y_test;
	Image img_az, img_am, img_vm, img_vd;
	
	Pecas() {
		this.setBounds(0, 0, 705, 705);
		this.setOpaque(false);
		x_test = 12*47;
		y_test = 11*47;
		img_az = new ImageIcon("PecaAzul.png").getImage();
		img_am = new ImageIcon("PecaAmarela.png").getImage();
		img_vm = new ImageIcon("PecaVermelha.png").getImage();
		img_vd = new ImageIcon("PecaVerde.png").getImage();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_az, coordPeca[0][0][0], coordPeca[0][0][1], null);
		g.drawImage(img_az, coordPeca[0][1][0], coordPeca[0][1][1], null);
		g.drawImage(img_az, coordPeca[0][2][0], coordPeca[0][2][1], null);
		
		g.drawImage(img_am, coordPeca[1][0][0], coordPeca[1][0][1], null);
		g.drawImage(img_am, coordPeca[1][1][0], coordPeca[1][1][1], null);
		g.drawImage(img_am, coordPeca[1][2][0], coordPeca[1][2][1], null);
		
		g.drawImage(img_vm, coordPeca[2][0][0], coordPeca[2][0][1], null);
		g.drawImage(img_vm, coordPeca[2][1][0], coordPeca[2][1][1], null);
		g.drawImage(img_vm, coordPeca[2][2][0], coordPeca[2][2][1], null);
		
		g.drawImage(img_vd, coordPeca[3][0][0], coordPeca[3][0][1], null);
		g.drawImage(img_vd, coordPeca[3][1][0], coordPeca[3][1][1], null);
		g.drawImage(img_vd, coordPeca[3][2][0], coordPeca[3][2][1], null);
	}
}
