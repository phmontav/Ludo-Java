package servidor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PecasServidor extends JPanel {
	
	static public int[][][] backup =  {{{11*47+27, 12*47+10}, {12*47, 12*47-20}, {13*47-27, 12*47+10}},
			  					{{11*47+27, 2*47+10}, {12*47, 2*47-20}, {13*47-27, 2*47+10}},
			  					{{1*47+27, 2*47+10}, {2*47, 2*47-20}, {3*47-27, 2*47+10}},
			  					{{1*47+27, 12*47+10}, {2*47, 12*47-20}, {3*47-27, 12*47+10}}};			//Serv?
	static public int[][][] coordPeca = {{{11*47+27, 12*47+10}, {12*47, 12*47-20}, {13*47-27, 12*47+10}},
								  {{11*47+27, 2*47+10}, {12*47, 2*47-20}, {13*47-27, 2*47+10}},
								  {{1*47+27, 2*47+10}, {2*47, 2*47-20}, {3*47-27, 2*47+10}},
								  {{1*47+27, 12*47+10}, {2*47, 12*47-20}, {3*47-27, 12*47+10}}};		//Cli
	static public int[][] posPeca = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};					//Cli?
	static public int[][] p_juntas = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};								//Serv
	static Image[] img_az = new Image[3], img_am = new Image[3], img_vm = new Image[3], img_vd = new Image[3];
	
	public PecasServidor() {
		this.setBounds(0, 0, 705, 705);
		this.setOpaque(false);
		img_az[0] = new ImageIcon("PecaAzul.png").getImage();
		img_az[1] = new ImageIcon("PecaAzul2.png").getImage();
		img_az[2] = new ImageIcon("PecaAzul3.png").getImage();
		img_am[0] = new ImageIcon("PecaAmarela.png").getImage();
		img_am[1] = new ImageIcon("PecaAmarela2.png").getImage();
		img_am[2] = new ImageIcon("PecaAmarela3.png").getImage();
		img_vm[0] = new ImageIcon("PecaVermelha.png").getImage();
		img_vm[1] = new ImageIcon("PecaVermelha2.png").getImage();
		img_vm[2] = new ImageIcon("PecaVermelha3.png").getImage();
		img_vd[0] = new ImageIcon("PecaVerde.png").getImage();
		img_vd[1] = new ImageIcon("PecaVerde2.png").getImage();
		img_vd[2] = new ImageIcon("PecaVerde3.png").getImage();
		
	}
	
	static void numerar(int turno, int n, int peca) {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img_az[p_juntas[0][0]-1], coordPeca[0][0][0], coordPeca[0][0][1], null);
		g.drawImage(img_az[p_juntas[0][1]-1], coordPeca[0][1][0], coordPeca[0][1][1], null);
		g.drawImage(img_az[p_juntas[0][2]-1], coordPeca[0][2][0], coordPeca[0][2][1], null);
		
		g.drawImage(img_am[p_juntas[1][0]-1], coordPeca[1][0][0], coordPeca[1][0][1], null);
		g.drawImage(img_am[p_juntas[1][1]-1], coordPeca[1][1][0], coordPeca[1][1][1], null);
		g.drawImage(img_am[p_juntas[1][2]-1], coordPeca[1][2][0], coordPeca[1][2][1], null);
		
		g.drawImage(img_vm[p_juntas[2][0]-1], coordPeca[2][0][0], coordPeca[2][0][1], null);
		g.drawImage(img_vm[p_juntas[2][1]-1], coordPeca[2][1][0], coordPeca[2][1][1], null);
		g.drawImage(img_vm[p_juntas[2][2]-1], coordPeca[2][2][0], coordPeca[2][2][1], null);
		
		g.drawImage(img_vd[p_juntas[3][0]-1], coordPeca[3][0][0], coordPeca[3][0][1], null);
		g.drawImage(img_vd[p_juntas[3][1]-1], coordPeca[3][1][0], coordPeca[3][1][1], null);
		g.drawImage(img_vd[p_juntas[3][2]-1], coordPeca[3][2][0], coordPeca[3][2][1], null);
	}
}
